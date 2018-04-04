package model;

import java.util.HashMap;

public class ListeDeSons {
	private static HashMap<String, Son> listeDesSons = new HashMap<String, Son>();
	
	public void addSon(Son son) {
		listeDesSons.put(son.getName(), son);
	}
	
	public Son getSonByName(String name) {
		return listeDesSons.get(name);
	}
	
	public void deleteSonByName(String name) {
		listeDesSons.remove(name);
	}

	public static HashMap<String, Son> getListeDesSons() {
		return listeDesSons;
	}

	public static void setListeDesSons(HashMap<String, Son> listeDesSons) {
		ListeDeSons.listeDesSons = listeDesSons;
	}
	
	
}
