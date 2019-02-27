/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author SR
 */
public class ListeDesSons {

    private static HashMap<String, Son> listeDesSons = new HashMap<String, Son>();

    static public void addSon(Son son) {
        listeDesSons.put(son.getName(), son);
    }

    static public Son getSonByName(String name) {
        return listeDesSons.get(name);
    }

    static public void deleteSonByName(String name) {
        listeDesSons.remove(name);
    }

    public static HashMap<String, Son> getListeDesSons() {
        return listeDesSons;
    }

    public static void setListeDesSons(HashMap<String, Son> listeDesSons) {
        ListeDesSons.listeDesSons = listeDesSons;
    }
}
