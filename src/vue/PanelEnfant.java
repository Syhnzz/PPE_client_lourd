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

import controleur.Enfant;
import controleur.Controleur;
import controleur.Tableau;

public class PanelEnfant extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtAge = new JTextField();  
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableEnfants ; //table des classes 
	private JScrollPane uneScroll ;
	
	//panel de filtrage 
	private JPanel panelFiltre = new JPanel(); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private Tableau unTableau ; 
	
	public PanelEnfant()
	{
		super ();
		//construction du formulaire Utilisateur. 
		this.panelForm.setLayout(new GridLayout(4,2));
		this.panelForm.setBackground(new Color (0, 255, 255));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("Nom Enfant :")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Prenom Enfant :")); 
		this.panelForm.add(this.txtPrenom); 
		this.panelForm.add(new JLabel("age :")); 
		this.panelForm.add(this.txtAge); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer); 
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction de la table des classes 
		String entetes [] = {"ID Enfant", "Nom Enfant", "Prenom Enfant", "Age"};
		this.unTableau = new Tableau (entetes, this.remplirDonnees("")); 
		
		this.tableEnfants = new JTable(this.unTableau) ; 
		this.tableEnfants.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableEnfants); 
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		
		//construction du panel filtre 
		this.panelFiltre.setBounds(350, 30, 450, 30);
		this.panelFiltre.setBackground(new Color (0, 255, 255));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les enfants par :")); 
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
		
		ArrayList<Enfant> lesEnfants = Controleur.selectAllEnfants(filtre) ; 
		Object [][] matrice = new Object [lesEnfants.size()][6];
		int i =0; 
		for (Enfant unEnfant : lesEnfants) {
			matrice [i][0] = unEnfant.getId_enfant(); 
			matrice [i][1] = unEnfant.getNom(); 
			matrice [i][2] = unEnfant.getPrenom(); 
			matrice [i][3] = unEnfant.getAge();
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtAge.setText("");
			 this.txtPrenom.setText("");
			 this.txtNom.setText("");
		 }
		 else if (e.getSource() == this.btEnregistrer) {
			 String nom = this.txtNom.getText(); 
			 String prenom = this.txtPrenom.getText();
			 int age = Integer.parseInt(this.txtAge.getText());
			 
			 //instanciation d'une classe 
			 Enfant unEnfant = new Enfant (nom,prenom, age); 
			 
			 //insertion dans la base de données 
			 Controleur.insertEnfant(unEnfant);
			 JOptionPane.showMessageDialog(this, "Enfant ajoutée avec succès");
			
			 //recuperation de l'id de la classe de la BDD 
			 unEnfant = Controleur.selectWhereEnfant(nom, prenom); 
			 
			 //mettre à jour l'afffichage 
			 Object ligne[]= {unEnfant.getId_enfant(), nom, prenom, age};
			 this.unTableau.ajouterLigne(ligne);
			 
			 this.txtAge.setText("");
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

























