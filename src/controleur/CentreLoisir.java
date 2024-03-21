package controleur;

public class CentreLoisir {
	private int idcl; 
	private String ville; 
	private int id_enfant;
	public CentreLoisir(int idcl, String ville, int id_enfant) {
		 
		this.idcl= idcl;
		this.ville = ville;
		this.id_enfant = id_enfant;
	}
	public CentreLoisir(String ville, int id_enfant) {
		 
		this.idcl = 0;
		this.ville = ville;
		this.id_enfant = id_enfant;
	}
	public int getIdcl() {
		return idcl;
	}
	public void setIdcl(int idcl) {
		this.idcl = idcl;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getId_enfant() {
		return id_enfant;
	}
	public void setId_enfant(int id_enfant) {
		this.id_enfant = id_enfant;
	}
	
}
 