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

import controleur.CentreLoisir;
import controleur.Controleur;
import controleur.Tableau;
import controleur.Enfant;

public class PanelCentreLoisir extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JTextField txtVille = new JTextField();  
	private JComboBox<String> txtId_enfant = new JComboBox<String>(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableCentreLoisirs ; //table des classes 
	private JScrollPane uneScroll ;
	
	//panel de filtrage 
	private JPanel panelFiltre = new JPanel(); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private Tableau unTableau ; 
	
	public PanelCentreLoisir()
	{
		super ();
		//construction du formulaire CentreLoisir. 
		this.panelForm.setLayout(new GridLayout(4,2));
		this.panelForm.setBackground(new Color (0, 255, 255));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("Ville :")); 
		this.panelForm.add(this.txtVille); 
		this.panelForm.add(new JLabel("ID Enfant:")); 
		this.panelForm.add(this.txtId_enfant); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer); 
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction de la table des classes 
		String entetes [] = {"ID CentreLoisir", "Ville", "ID Enfant"};
		this.unTableau = new Tableau (entetes, this.remplirDonnees("")); 
		
		this.tableCentreLoisirs = new JTable(this.unTableau) ; 
		this.tableCentreLoisirs.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableCentreLoisirs); 
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		
		//construction du panel filtre 
		this.panelFiltre.setBounds(350, 30, 450, 30);
		this.panelFiltre.setBackground(new Color (0, 255, 255));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les inscriptions par :")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer); 
		this.add(this.panelFiltre); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
		
		this.remplirCBXEnfant ();
	}
	

	private void remplirCBXEnfant() {
		this.txtId_enfant.removeAllItems();
		ArrayList<Enfant> lesEnfants = Controleur.selectAllEnfants("");
		for (Enfant unEnfant : lesEnfants) {
			this.txtId_enfant.addItem(unEnfant.getId_enfant()+"-"
					+ unEnfant.getNom());
		}
		
	}
	
	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertir l'ArrayList en une matrice de données.
		
		ArrayList<CentreLoisir> lesCentreLoisirs = Controleur.selectAllCentreLoisirs(filtre) ; 
		Object [][] matrice = new Object [lesCentreLoisirs.size()][3];
		int i =0; 
		for (CentreLoisir unCentreLoisir : lesCentreLoisirs) {
			matrice [i][0] = unCentreLoisir.getIdcl(); 
			matrice [i][1] = unCentreLoisir.getVille(); 
			matrice [i][2] = unCentreLoisir.getId_enfant(); 
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtVille.setText("");
		 }
		 else if (e.getSource() == this.btEnregistrer) {
			 String ville = this.txtVille.getText(); 
			 String chaine = this.txtId_enfant.getSelectedItem().toString();
			 String tab[] = chaine.split("-");
			 int id_enfant  = Integer.parseInt(tab[0]);
			 
			 //instanciation d'une classe 
			 CentreLoisir unCentreLoisir = new CentreLoisir (ville, id_enfant); 
			 
			 //insertion dans la base de données 
			 Controleur.insertCentreLoisir(unCentreLoisir);
			 JOptionPane.showMessageDialog(this, "Inscription ajoutée avec succès");
			
			 //recuperation de l'id de la classe de la BDD 
			 unCentreLoisir = Controleur.selectWhereCentreLoisir(ville); 
			 
			 //mettre à jour l'afffichage 
			 Object ligne[]= {unCentreLoisir.getIdcl(), ville, id_enfant};
			 this.unTableau.ajouterLigne(ligne);
			 
			 this.txtVille.setText("");
		 }
		 else if (e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText(); 
			 Object matrice [][] = this.remplirDonnees(filtre); 
			 this.unTableau.setDonnees(matrice);
		 }
	}
}
























