package controleur;

public class Activite 
{
	private int id_act; 
	private String nom, date_act, statut;
	public Activite(int id_act, String nom, String date_act, String statut) {
		 
		this.id_act = id_act;
		this.nom = nom;
		this.date_act = date_act;
		this.statut = statut;
	} 
	public Activite(  String nom, String date_act, String statut) {
		 
		this.id_act = 0;
		this.nom = nom;
		this.date_act = date_act;
		this.statut = statut;
	}
	public int getId_act() {
		return id_act;
	}
	public void setId_act(int id_act) {
		this.id_act = id_act;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDate_act() {
		return date_act;
	}
	public void setDate_act(String date_act) {
		this.date_act = date_act;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	} 
	
}
 