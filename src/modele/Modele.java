package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Activite;
import controleur.CentreLoisir;
import controleur.Enfant;
import controleur.RDVMairie;
import controleur.User;
import controleur.Utilisateur;

public class Modele {
	private static Bdd uneBdd = new Bdd ("localhost","mairieV","root","");
	
	public static User selectWhereUser (String email, String mdp) {
		String requete = "select * from user where email='"+email+"' and "
				+ " mdp ='"+mdp+"' ; ";
		User unUser = null; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unUser = new User (
						unRes.getInt("iduser"), unRes.getString("nom"), 
						unRes.getString("prenom"), unRes.getString("email"), 
						unRes.getString("mdp"), unRes.getString("role")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unUser; 
	}
	/*************** Gestion des RDVMairie ***************************/ 
	public static void insertRDVMairie (RDVMairie unRDVMairie) {
		String requete ="insert into rdvMairie values (null, '"
				+unRDVMairie.getMotif()+"','"
				+unRDVMairie.getRdv_date()+"','"
				+unRDVMairie.getService()+"','"
				+unRDVMairie.getId_user()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	public static void deleteRDVMairie (int id_Rdv) {
		String requete ="delete from rdvMairie where id_rdv = "+id_Rdv+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static void updateRDVMairie (RDVMairie unRDVMairie) {
		String requete ="update rdvMairie set motif='" 
				+unRDVMairie.getMotif()+"', rdv_date ='"
				+unRDVMairie.getRdv_date()+"', where service = "
				+unRDVMairie.getService()+"' where id_rdv = "
				+unRDVMairie.getId_rdv()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static ArrayList<RDVMairie> selectAllRDVMairie (String filtre){
		ArrayList<RDVMairie> lesRDVMairie = new  ArrayList<RDVMairie>(); 
		String requete ="" ; 
		if (filtre.contentEquals("")) {
			requete = "select * from  rdvMairie ; ";
		}else {
			requete = "select * from  rdvMairie where motif like '%" + filtre 
					+ "%'  or  salle like '%"+filtre
					+ "%' or diplome like '%"+filtre +"%' ;"; 
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				RDVMairie unRDVMairie = new RDVMairie(desRes.getInt("id_rdv"),
						desRes.getString("motif"), 
						desRes.getString("rdv_date"),
						desRes.getString("service"),desRes.getInt("id_user"));
				lesRDVMairie.add(unRDVMairie);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return lesRDVMairie; 
	}
	public static RDVMairie selectWhereRDVMairie (int id_rdv) {
		RDVMairie unRDVMairie = null; 
		String requete = "select * from rdvMairie where id_rdv = " +id_rdv+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unRDVMairie = new RDVMairie(desRes.getInt("id_rdv"),
						desRes.getString("motif"), 
						desRes.getString("rdv_date"),
						desRes.getString("service"),desRes.getInt("iduser"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unRDVMairie; 
	}
	public static RDVMairie selectWhereRDVMairie (String motif, String rdv_date) {
		RDVMairie unRDVMairie = null; 
		String requete = "select * from rdvMairie where motif='"+motif+"' and rdv_date='"+rdv_date+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unRDVMairie = new RDVMairie(desRes.getInt("id_rdv"),
						desRes.getString("motif"), 
						desRes.getString("rdv_date"),
						desRes.getString("service"),desRes.getInt("id_user"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unRDVMairie; 
		
	}
		
		public static void updateUser(User unUser) {
			String requete = "update user set nom='"
					+unUser.getNom()+"' , prenom ='"
					+unUser.getPrenom()+"' , email ='"
					+unUser.getEmail()+"', role ='"
					+unUser.getRole()+"' , mdp ='"
					+unUser.getMdp()+"'  where iduser ="
					+unUser.getIduser()+";";
			
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement(); 
				unStat.execute(requete);
				unStat.close(); 
				uneBdd.seDeConnecter();
			}
			catch (SQLException exp) {
				System.out.println("Erreur de requete : " + requete);
			}
		
	}
	
	/*************** Gestion des Utilisateur ***************************/ 
	public static void insertUtilisateur (Utilisateur unUtilisateur) {
		String requete ="insert into utilisateur values (null, '"
				+unUtilisateur.getNom()+"','"
				+unUtilisateur.getPrenom()+"','"
				+unUtilisateur.getEmail()+"','"
				+unUtilisateur.getAge()+"','"
				+unUtilisateur.getPassword_hash()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	public static void deleteUtilisateur (int id_user) {
		String requete ="delete from utilisateur where idclasse = "+id_user+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static void updateUtilisateur (Utilisateur unUtilisateur) {
		String requete ="update utlisateur set nom='" 
				+unUtilisateur.getNom()+"', prenom ='"
				+unUtilisateur.getPrenom()+"', email ='"
				+unUtilisateur.getEmail()+"', date naissance ='"
				+unUtilisateur.getAge()+"', statut ='"
				+unUtilisateur.getPassword_hash()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static ArrayList<Utilisateur> selectAllUtilisateurs (String filtre){
		ArrayList<Utilisateur> lesUtilisateurs = new  ArrayList<Utilisateur>(); 
		String requete ="" ; 
		if (filtre.contentEquals("")) {
			requete = "select * from utilisateur ; ";
		}else {
			requete = "select * from  utilisateur where nom like '%" + filtre 
					+ "%'  or  prenom like '%"+filtre
					+ "%' or email like '%"+filtre 
					+ "%' or age like '%"+filtre 
					+ "%' or password_hash like '%"+filtre +"%' ;"; 
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Utilisateur unUtilisateur = new Utilisateur(desRes.getInt("id_user"),
						desRes.getString("nom"), 
						desRes.getString("prenom"),
						desRes.getString("email"),
						desRes.getInt("age"),
						desRes.getString("password_hash"));
				lesUtilisateurs.add(unUtilisateur);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return lesUtilisateurs; 
	}
	public static Utilisateur selectWhereUtilisateur (int id_user) {
		Utilisateur unUtilisateur = null; 
		String requete = "select * from utilisateur where id_user= " +id_user+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unUtilisateur = new Utilisateur(desRes.getInt("id_user"),
						desRes.getString("nom"), 
						desRes.getString("prenom"),
						desRes.getString("email"),
						desRes.getInt("age"),
						desRes.getString("password_hash"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unUtilisateur; 
	}
	public static Utilisateur selectWhereUtilisateur (String nom, String email) {
		Utilisateur unUtilisateur = null; 
		String requete = "select * from utilisateur where nom='"+nom+"' and email='"+email+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unUtilisateur = new Utilisateur(desRes.getInt("id_user"),
						desRes.getString("nom"), 
						desRes.getString("prenom"),
						desRes.getString("email"),
						desRes.getInt("age"),
						desRes.getString("password_hash"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unUtilisateur; 
	}
	
	/*************** Gestion des Activite ***************************/ 
	public static void insertActivite (Activite uneActivite) {
		String requete ="insert into activite values (null, '"
				+uneActivite.getNom()+"','"
				+uneActivite.getDate_act()+"','"
				+uneActivite.getStatut()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	public static void deleteActivite (int id_act) {
		String requete ="delete from activite where id_act= "+id_act+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static void updateActivite (Activite uneActivite) {
		String requete ="update activite set nom='" 
				+uneActivite.getNom()+"', date_act ='"
				+uneActivite.getDate_act()+"', statut ='"
				+uneActivite.getStatut()+"' where id_act = "
				+uneActivite.getId_act()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static ArrayList<Activite> selectAllActivites (String filtre){
		ArrayList<Activite> lesActivites = new  ArrayList<Activite>(); 
		String requete ="" ; 
		if (filtre.contentEquals("")) {
			requete = "select * from  activite ; ";
		}else {
			requete = "select * from  activite where nom like '%" + filtre 
					+ "%'  or  nom like '%"+filtre
					+ "%' or date_act like '%"+filtre 
					+ "%' or statut like '%"+filtre 
					+ "%' or id_act like '%"+filtre +"%' ;"; 
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Activite uneActivite = new Activite(desRes.getInt("id_act"),
						desRes.getString("nom"), 
						desRes.getString("date_act"),
						desRes.getString("statut"));
				lesActivites.add(uneActivite);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return lesActivites; 
	}
	public static Activite selectWhereActivite (int id_act) {
		Activite uneActivite = null; 
		String requete = "select * from activite where id_act = " +id_act+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				uneActivite = new Activite(desRes.getInt("id_act"),
						desRes.getString("nom"), 
						desRes.getString("date_act"),
						desRes.getString("statut"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return uneActivite; 
	}
	public static Activite selectWhereActivite (String nom, String date_act) {
		Activite uneActivite = null; 
		String requete = "select * from activite where nom='"+nom+"' and date_act='"+date_act+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				uneActivite = new Activite(desRes.getInt("id_act"),
						desRes.getString("nom"), 
						desRes.getString("date_act"),
						desRes.getString("statut"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return uneActivite; 
	}
	
	/*************** Gestion des Utilisateur ***************************/ 
	public static void insertEnfant (Enfant unEnfant) {
		String requete ="insert into enfant values (null, '"
				+unEnfant.getNom()+"','"
				+unEnfant.getPrenom()+"','"
				+unEnfant.getAge()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	public static void deleteEnfant (int id_enfant) {
		String requete ="delete from enfant where id_enfant = "+id_enfant+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static void updateEnfant (Enfant unEnfant) {
		String requete ="update enfant set nom='" 
				+unEnfant.getNom()+"', prenom ='"
				+unEnfant.getPrenom()+"', age='"
				+unEnfant.getAge()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static ArrayList<Enfant> selectAllEnfants (String filtre){
		ArrayList<Enfant> lesEnfants = new  ArrayList<Enfant>(); 
		String requete ="" ; 
		if (filtre.contentEquals("")) {
			requete = "select * from enfant ; ";
		}else {
			requete = "select * from  enfant where nom like '%" + filtre 
					+ "%'  or  prenom like '%"+filtre
					+ "%' or age like '%"+filtre +"%' ;"; 
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Enfant unEnfant = new Enfant(desRes.getInt("id_enfant"),
						desRes.getString("nom"), 
						desRes.getString("prenom"),
						desRes.getInt("age"));
				lesEnfants.add(unEnfant);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return lesEnfants; 
	}
	public static Enfant selectWhereEnfant (int id_enfant) {
		Enfant unEnfant = null; 
		String requete = "select * from enfant where id_enfant= " +id_enfant+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unEnfant = new Enfant(desRes.getInt("id_enfant"),
						desRes.getString("nom"), 
						desRes.getString("prenom"),
						desRes.getInt("age"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unEnfant; 
	}
	public static Enfant selectWhereEnfant (String nom, String prenom) {
		Enfant unEnfant = null; 
		String requete = "select * from enfant where nom='"+nom+"' and prenom='"+prenom+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unEnfant = new Enfant(desRes.getInt("id_enfant"),
						desRes.getString("nom"), 
						desRes.getString("prenom"),
						desRes.getInt("age"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unEnfant; 
	}
	
	/*************** Gestion des CentreLoisir ***************************/ 
	public static void insertCentreLoisir (CentreLoisir unCentreLoisir) {
		String requete ="insert into centreLoisir values (null, '"
				+unCentreLoisir.getVille()+"','"
				+unCentreLoisir.getId_enfant()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	public static void deleteCentreLoisir (int idcl) {
		String requete ="delete from centreLoisir where idcl = "+idcl+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static void updateCentreLoisir (CentreLoisir unCentreLoisir) {
		String requete ="update centreLoisir set ville='" 
				+unCentreLoisir.getVille()+"', where id_enfant = "
				+unCentreLoisir.getId_enfant()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	public static ArrayList<CentreLoisir> selectAllCentreLoisirs (String filtre){
		ArrayList<CentreLoisir> lesCentreLoisirs = new  ArrayList<CentreLoisir>(); 
		String requete ="" ; 
		if (filtre.contentEquals("")) {
			requete = "select * from  centreLoisir ; ";
		}else {
			requete = "select * from  centreLoisir where ville like '%" + filtre +"%' ;"; 
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				CentreLoisir unCentreLoisir = new CentreLoisir(desRes.getInt("idcl"),
						desRes.getString("ville"), 
						desRes.getInt("id_enfant"));
				lesCentreLoisirs.add(unCentreLoisir);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return lesCentreLoisirs; 
	}
	public static CentreLoisir selectWhereCentreLoisir (int idcl) {
		CentreLoisir unCentreLoisir = null; 
		String requete = "select * from centreLoisir where idcl = " +idcl+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unCentreLoisir = new CentreLoisir(desRes.getInt("idcl"),
						desRes.getString("ville"), 
						desRes.getInt("id_enfant"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unCentreLoisir; 
	}
	public static CentreLoisir selectWhereCentreLoisir (String ville) {
		CentreLoisir unCentreLoisir = null; 
		String requete = "select * from centreLoisir where ville='"+ville+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unCentreLoisir = new CentreLoisir(desRes.getInt("idcl"),
						desRes.getString("ville"), 
						desRes.getInt("id_enfant"));
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unCentreLoisir; 
	}
	
}





















