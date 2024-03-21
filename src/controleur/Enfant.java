package controleur;

public class Enfant {
	private int id_enfant ; 
	private String nom, prenom; 
	private int age;
	
	public Enfant(int id_enfant, String nom, String prenom, int age) {
		 
		this.id_enfant = id_enfant;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	} 
	public Enfant(  String nom, String prenom, int age) {
		 
		this.id_enfant = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}
	public int getId_enfant() {
		return id_enfant;
	}
	public void setId_user(int id_enfant) {
		this.id_enfant = id_enfant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}


 