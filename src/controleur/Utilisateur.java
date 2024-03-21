package controleur;

public class Utilisateur {
	private int id_user ; 
	private String nom, prenom, email; 
	private int age;
	private String password_hash;
	
	public Utilisateur(int id_user, String nom, String prenom, String email, int age, String password_hash) {
		 
		this.id_user = id_user;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.age = age;
		this.password_hash = password_hash;
	} 
	public Utilisateur(  String nom, String prenom, String email, int age, String password_hash) {
		 
		this.id_user = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.age = age;
		this.password_hash = password_hash;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setStatut(String password_hash) {
		this.password_hash = password_hash;
	}
	
}


 