package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Utilisateur;
import controleur.Controleur;
import controleur.Tableau;

public class PanelUtilisateur extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtEmail = new JTextField(); 
	private JTextField txtAge = new JTextField();  
	private JTextField txtPassword_hash = new JTextField();  
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableUtilisateurs ; //table des classes 
	private JScrollPane uneScroll ;
	
	//panel de filtrage 
	private JPanel panelFiltre = new JPanel(); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private Tableau unTableau ; 
	
	public PanelUtilisateur()
	{
		super ();
		//construction du formulaire Utilisateur. 
		this.panelForm.setLayout(new GridLayout(6,2));
		this.panelForm.setBackground(new Color (225, 198, 22));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("Nom Utilisateur :")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Prenom Utilisateur :")); 
		this.panelForm.add(this.txtPrenom); 
		this.panelForm.add(new JLabel("email :")); 
		this.panelForm.add(this.txtEmail); 
		this.panelForm.add(new JLabel("age :")); 
		this.panelForm.add(this.txtAge); 
		this.panelForm.add(new JLabel("Mot de passe :")); 
		this.panelForm.add(this.txtPassword_hash); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer); 
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction de la table des classes 
		String entetes [] = {"ID User", "Nom Utilisateur", "Prenom Utilisateur","Email","Age", "Mdp"};
		this.unTableau = new Tableau (entetes, this.remplirDonnees("")); 
		
		this.tableUtilisateurs = new JTable(this.unTableau) ; 
		this.tableUtilisateurs.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableUtilisateurs); 
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		
		//construction du panel filtre 
		this.panelFiltre.setBounds(350, 30, 450, 30);
		this.panelFiltre.setBackground(new Color (225, 198, 22));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les utilisateurs par :")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer); 
		this.add(this.panelFiltre); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
	}
	
	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertir l'ArrayList en une matrice de données.
		
		ArrayList<Utilisateur> lesUtilisateurs = Controleur.selectAllUtilisateurs(filtre) ; 
		Object [][] matrice = new Object [lesUtilisateurs.size()][6];
		int i =0; 
		for (Utilisateur unUtilisateur : lesUtilisateurs) {
			matrice [i][0] = unUtilisateur.getId_user(); 
			matrice [i][1] = unUtilisateur.getNom(); 
			matrice [i][2] = unUtilisateur.getPrenom(); 
			matrice [i][3] = unUtilisateur.getEmail();
			matrice [i][4] = unUtilisateur.getAge();
			matrice [i][5] = unUtilisateur.getPassword_hash();
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtPassword_hash.setText("");
			 this.txtAge.setText("");
			 this.txtEmail.setText("");
			 this.txtPrenom.setText("");
			 this.txtNom.setText("");
		 }
		 else if (e.getSource() == this.btEnregistrer) {
			 String nom = this.txtNom.getText(); 
			 String prenom = this.txtPrenom.getText();
			 String email = this.txtEmail.getText();
			 int age = Integer.parseInt(this.txtAge.getText());
			 String password_hash = this.txtPassword_hash.getText();
			 
			 //instanciation d'une classe 
			 Utilisateur unUtilisateur = new Utilisateur (nom,prenom, email, age, password_hash); 
			 
			 //insertion dans la base de données 
			 Controleur.insertUtilisateur(unUtilisateur);
			 JOptionPane.showMessageDialog(this, "Utilisateur ajoutée avec succès");
			
			 //recuperation de l'id de la classe de la BDD 
			 unUtilisateur = Controleur.selectWhereUtilisateur(nom, email); 
			 
			 //mettre à jour l'afffichage 
			 Object ligne[]= {unUtilisateur.getId_user(), nom, prenom, email, age, password_hash};
			 this.unTableau.ajouterLigne(ligne);
			 
			 this.txtPassword_hash.setText("");
			 this.txtAge.setText("");
			 this.txtEmail.setText("");
			 this.txtPrenom.setText("");
			 this.txtNom.setText("");
			 
		 }
		 else if (e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText(); 
			 Object matrice [][] = this.remplirDonnees(filtre); 
			 this.unTableau.setDonnees(matrice);
		 }
	}
}

























