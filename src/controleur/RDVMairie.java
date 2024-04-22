package controleur;

public class RDVMairie {
	private int id_rdv; 
	private String rdv_date, rdv_heure, service; 
	private int id_user;
	public RDVMairie(int id_rdv, String rdv_date, String rdv_heure, String service, int id_user) {
		 
		this.id_rdv = id_rdv;
		this.rdv_date = rdv_date;
		this.rdv_heure = rdv_heure;
		this.service = service;
		this.id_user = id_user;
	}
	public RDVMairie(String rdv_date, String rdv_heure, String service, int id_user) {
		 
		this.id_rdv = 0;
		this.rdv_date = rdv_date;
		this.rdv_heure = rdv_heure;
		this.service = service;
		this.id_user = id_user;
	}
	public int getId_rdv() {
		return id_rdv;
	}
	public void setId_rdv(int id_rdv) {
		this.id_rdv = id_rdv;
	}
	public String getRdv_date() {
		return rdv_date;
	}
	public void setRdv_date(String rdv_date) {
		this.rdv_date = rdv_date;
	}
	public String getRdv_heure() {
		return rdv_heure;
	}
	public void setRdv_heure(String rdv_heure) {
		this.rdv_heure = rdv_heure;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
}
 