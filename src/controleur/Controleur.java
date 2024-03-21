package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {
	
	public static User selectWhereUser (String email, String mdp) {
		//tester email et la complexite mdp. 
		return Modele.selectWhereUser(email, mdp);
	}
	/****************** gestion des rendez vous ***************/
	public static void insertRDVMairie( RDVMairie unRDVMairie) {
		Modele.insertRDVMairie(unRDVMairie);
	}
	
	public static void deleteRDVMairie( int id_Rdv) {
		Modele.deleteRDVMairie(id_Rdv);
	}
	
	public static void updateRDVMairie( RDVMairie unRDVMairie) {
		Modele.updateRDVMairie(unRDVMairie);
	}
	
	public static ArrayList<RDVMairie> selectAllRDVMairie( String filtre) {
		return Modele.selectAllRDVMairie(filtre);
	}
	public static RDVMairie selectWhereRDVMairie(int id_Rdv) {
		return Modele.selectWhereRDVMairie(id_Rdv);
	}
	public static void updateUser(User unUser) {
		Modele.updateUser(unUser); 
		
	}
	
	public static RDVMairie selectWhereRDVMairie (String motif, String rdv_date) {
		return Modele.selectWhereRDVMairie(motif, rdv_date);
	}
	
	/****************** gestion des utilisateurs ***************/
	public static void insertUtilisateur( Utilisateur unUtilisateur) {
		Modele.insertUtilisateur(unUtilisateur);
	}
	
	public static void deleteUtilisateur( int id_user) {
		Modele.deleteUtilisateur(id_user);
	}
	
	public static void updateUtilisateur( Utilisateur unUtilisateur) {
		Modele.updateUtilisateur(unUtilisateur);
	}
	
	public static ArrayList<Utilisateur> selectAllUtilisateurs( String filtre) {
		return Modele.selectAllUtilisateurs(filtre);
	}
	public static Utilisateur selectWhereUtilisateur(int id_user) {
		return Modele.selectWhereUtilisateur(id_user);
	}
	
	public static Utilisateur selectWhereUtilisateur (String nom, String email) {
		return Modele.selectWhereUtilisateur(nom, email);
	}
	
	/****************** gestion des Activité ***************/
	public static void insertActivite( Activite uneActivite) {
		Modele.insertActivite(uneActivite);
	}
	
	public static void deleteActivite( int id_act) {
		Modele.deleteActivite(id_act);
	}
	
	public static void updateActivite( Activite uneActivite) {
		Modele.updateActivite(uneActivite);
	}
	
	public static ArrayList<Activite> selectAllActivites( String filtre) {
		return Modele.selectAllActivites(filtre);
	}
	public static Activite selectWhereActivite(int id_act) {
		return Modele.selectWhereActivite(id_act);
	}
	
	public static Activite selectWhereActivite (String nom, String date_act) {
		return Modele.selectWhereActivite(nom, date_act);
	}
	
	/****************** gestion des Enfants ***************/
	public static void insertEnfant( Enfant unEnfant) {
		Modele.insertEnfant(unEnfant);
	}
	
	public static void deleteEnfant( int id_enfant) {
		Modele.deleteEnfant(id_enfant);
	}
	
	public static void updateEnfant( Enfant unEnfant) {
		Modele.updateEnfant(unEnfant);
	}
	
	public static ArrayList<Enfant> selectAllEnfants( String filtre) {
		return Modele.selectAllEnfants(filtre);
	}
	public static Enfant selectWhereEnfant(int id_enfant) {
		return Modele.selectWhereEnfant(id_enfant);
	}
	
	public static Enfant selectWhereEnfant (String nom, String prenom) {
		return Modele.selectWhereEnfant(nom, prenom);
	}
	
	/****************** gestion des CentreLoisir ***************/
	public static void insertCentreLoisir( CentreLoisir unCentreLoisir) {
		Modele.insertCentreLoisir(unCentreLoisir);
	}
	
	public static void deleteCentreLoisir( int idcl) {
		Modele.deleteActivite(idcl);
	}
	
	public static void updateCentreLoisir( CentreLoisir unCentreLoisir) {
		Modele.updateCentreLoisir(unCentreLoisir);
	}
	
	public static ArrayList<CentreLoisir> selectAllCentreLoisirs( String filtre) {
		return Modele.selectAllCentreLoisirs(filtre);
	}
	public static CentreLoisir selectWhereCentreLoisir(int idcl) {
		return Modele.selectWhereCentreLoisir(idcl);
	}
	
	public static CentreLoisir selectWhereCentreLoisir (String date_inscr) {
		return Modele.selectWhereCentreLoisir(date_inscr);
	}
	
	
	
}










