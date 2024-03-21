package controleur;

public class RDVMairie {
	private int id_rdv; 
	private String motif, rdv_date; 
	private int id_user;
	public RDVMairie(int id_rdv, String motif, String rdv_date, int id_user) {
		 
		this.id_rdv = id_rdv;
		this.motif = motif;
		this.rdv_date = rdv_date;
		this.id_user = id_user;
	}
	public RDVMairie(String motif, String rdv_date, int id_user) {
		 
		this.id_rdv = 0;
		this.motif = motif;
		this.rdv_date = rdv_date;
		this.id_user = id_user;
	}
	public int getId_rdv() {
		return id_rdv;
	}
	public void setId_rdv(int id_rdv) {
		this.id_rdv = id_rdv;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public String getRdv_date() {
		return rdv_date;
	}
	public void setRdv_date(String rdv_date) {
		this.rdv_date = rdv_date;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
}
 