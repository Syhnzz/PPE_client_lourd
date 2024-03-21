package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class Mairie {
	
	private static VueConnexion uneVueConnexion ; 
	private static VueGenerale uneVueGenerale ; 
	
	public static void main(String[] args) {
		 
		 uneVueConnexion = new VueConnexion(); 
	
	}
	
	public static void rendreVisibleConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}
	
	public static void rendreVisibleGenerale (boolean action, User unUser) {
		if (action == true) {
			uneVueGenerale = new VueGenerale(unUser); 
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose();
		}
	}

}