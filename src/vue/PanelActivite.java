package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Activite;
import controleur.Controleur;
import controleur.Tableau;

public class PanelActivite extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtDate_act = new JTextField(); 
	private JComboBox<String> txtStatut = new JComboBox<String>(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableEtudiants ; //table des classes 
	private JScrollPane uneScroll ;
	
	//panel de filtrage 
	private JPanel panelFiltre = new JPanel(); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private Tableau unTableau ; 
	
	public PanelActivite()
	{
		super ();
		//construction du formulaire Activite. 
		this.panelForm.setLayout(new GridLayout(4,2));
		this.panelForm.setBackground(new Color (225, 198, 22));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("Nom Activite :")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("date activite :")); 
		this.panelForm.add(this.txtDate_act); 
		this.panelForm.add(new JLabel("Type :")); 
		this.panelForm.add(this.txtStatut); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer); 
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction de la table des Activites
		String entetes [] = {"ID Activite", "Nom activite","Date Activite", 
		"Type"};
		this.unTableau = new Tableau (entetes, this.remplirDonnees("")); 
		
		this.tableEtudiants = new JTable(this.unTableau) ; 
		this.tableEtudiants.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableEtudiants); 
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		
		//construction du panel filtre 
		this.panelFiltre.setBounds(350, 30, 450, 30);
		this.panelFiltre.setBackground(new Color (225, 198, 22));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les activité par :")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer); 
		this.add(this.panelFiltre); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
		
		this.txtStatut.addItem("Sportif");
		this.txtStatut.addItem("Culturel");
		

	}



	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertir l'ArrayList en une matrice de données.
		
		ArrayList<Activite> lesActivites = Controleur.selectAllActivites(filtre) ; 
		Object [][] matrice = new Object [lesActivites.size()][4];
		int i =0; 
		for (Activite uneActivite : lesActivites) {
			matrice [i][0] = uneActivite.getId_act(); 
			matrice [i][1] = uneActivite.getNom(); 
			matrice [i][2] = uneActivite.getDate_act(); 
			matrice [i][3] = uneActivite.getStatut();
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 //this.txtIdclasse.setText("");
			 //this.txtStatut.setText("");
			 this.txtDate_act.setText("");
			 this.txtNom.setText("");
		 }
		 else if (e.getSource() == this.btEnregistrer) {
			 String nom = this.txtNom.getText(); 
			 String date_act = this.txtDate_act.getText();
			 String statut  = this.txtStatut.getSelectedItem().toString();

			 
			 //instanciation d'une classe 
			 Activite uneActivite = new Activite (nom, date_act, statut); 
			 
			 //insertion dans la base de données 
			 Controleur.insertActivite(uneActivite);
			 JOptionPane.showMessageDialog(this, "Activite ajoutée avec succès");
			
			 //recuperation de l'id de la classe de la BDD 
			 uneActivite = Controleur.selectWhereActivite(nom, date_act); 
			 
			 //mettre à jour l'afffichage 
			 Object ligne[]= {uneActivite.getId_act(), nom, date_act, statut};
			 this.unTableau.ajouterLigne(ligne);
			 
			 this.txtDate_act.setText("");
			 this.txtNom.setText("");
		 }
		 else if (e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText(); 
			 Object matrice [][] = this.remplirDonnees(filtre); 
			 this.unTableau.setDonnees(matrice);
		 }
	}
}

























