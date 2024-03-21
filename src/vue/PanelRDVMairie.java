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

import controleur.RDVMairie;
import controleur.Controleur;
import controleur.Tableau;
import controleur.Utilisateur;

public class PanelRDVMairie extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JTextField txtMotif = new JTextField(); 
	private JTextField txtRdv_date = new JTextField(); 
	private JComboBox<String> txtId_user = new JComboBox<String>(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableRDVMairie ; //table des classes 
	private JScrollPane uneScroll ;
	
	//panel de filtrage 
	private JPanel panelFiltre = new JPanel(); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private Tableau unTableau ; 
	
	public PanelRDVMairie()
	{
		super ();
		//construction du formulaire Classe. 
		this.panelForm.setLayout(new GridLayout(4,2));
		this.panelForm.setBackground(new Color (225, 198, 22));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("Motif :")); 
		this.panelForm.add(this.txtMotif); 
		this.panelForm.add(new JLabel("Rendez vous date :")); 
		this.panelForm.add(this.txtRdv_date); 
		this.panelForm.add(new JLabel("ID User:")); 
		this.panelForm.add(this.txtId_user); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer); 
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction de la table des classes 
		String entetes [] = {"ID RDVMairie", "Motif", "Rendez vous date", "ID User"};
		this.unTableau = new Tableau (entetes, this.remplirDonnees("")); 
		
		this.tableRDVMairie = new JTable(this.unTableau) ; 
		this.tableRDVMairie.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableRDVMairie); 
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		
		//construction du panel filtre 
		this.panelFiltre.setBounds(350, 30, 450, 30);
		this.panelFiltre.setBackground(new Color (225, 198, 22));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les classes par :")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer); 
		this.add(this.panelFiltre); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
		
		this.remplirCBXUtilisateur ();
	}
	
	private void remplirCBXUtilisateur() {
		this.txtId_user.removeAllItems();
		ArrayList<Utilisateur> lesUtilisateurs = Controleur.selectAllUtilisateurs("");
		for (Utilisateur unUtilisateur : lesUtilisateurs) {
			this.txtId_user.addItem(unUtilisateur.getId_user()+"-"
					+ unUtilisateur.getNom());
		}
		
	}
	
	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertir l'ArrayList en une matrice de données.
		
		ArrayList<RDVMairie> lesRDVMairie = Controleur.selectAllRDVMairie(filtre) ; 
		Object [][] matrice = new Object [lesRDVMairie.size()][4];
		int i =0; 
		for (RDVMairie unRDVMairie : lesRDVMairie) {
			matrice [i][0] = unRDVMairie.getId_rdv(); 
			matrice [i][1] = unRDVMairie.getMotif(); 
			matrice [i][2] = unRDVMairie.getRdv_date(); 
			matrice [i][3] = unRDVMairie.getId_user(); 
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtRdv_date.setText("");
			 this.txtMotif.setText("");
		 }
		 else if (e.getSource() == this.btEnregistrer) {
			 String motif = this.txtMotif.getText(); 
			 String rdv_date = this.txtRdv_date.getText();
			 String chaine = this.txtId_user.getSelectedItem().toString();
			 String tab[] = chaine.split("-");
			 int id_user  = Integer.parseInt(tab[0]);
			 
			 //instanciation d'une classe 
			 RDVMairie unRDVMairie = new RDVMairie (motif,rdv_date, id_user); 
			 
			 //insertion dans la base de données 
			 Controleur.insertRDVMairie(unRDVMairie);
			 JOptionPane.showMessageDialog(this, "Rendez vous ajoutée avec succès");
			
			 //recuperation de l'id de la classe de la BDD 
			 unRDVMairie = Controleur.selectWhereRDVMairie(motif, rdv_date); 
			 
			 //mettre à jour l'afffichage 
			 Object ligne[]= {unRDVMairie.getId_rdv(), motif, rdv_date, id_user};
			 this.unTableau.ajouterLigne(ligne);
			 
			 this.txtRdv_date.setText("");
			 this.txtMotif.setText("");
		 }
		 else if (e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText(); 
			 Object matrice [][] = this.remplirDonnees(filtre); 
			 this.unTableau.setDonnees(matrice);
		 }
	}
}
























