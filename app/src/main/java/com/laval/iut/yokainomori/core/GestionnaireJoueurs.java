package com.laval.iut.yokainomori.core;


import java.util.ArrayList;
import java.util.List;

/**
 * G�re 2 joueurs.
 * @author Nicolas
 *
 */
public class GestionnaireJoueurs {

	private Joueur[] joueurs = {null,null};
	private int indexJoueurActuel;
	private int indexJoueurAdverse;

    private List<JoueurListener> joueurListener = new ArrayList<>();

    public void addJoueurListeners(JoueurListener l) {
        joueurListener.add(l);
    }

    public GestionnaireJoueurs(String nom1,String nom2){
		joueurs[0] = new Joueur(nom1);
		joueurs[1] = new Joueur(nom2);
	}
	
	public Joueur getJoueur(int index) {
		return joueurs[index];
	}
	public Joueur getJoueur(String nom) {
		for (int i = 0;i<joueurs.length;i++)
			if (joueurs[i].getNom().equals(nom))
				return joueurs[i];
		return null;
	}
	public Joueur[] getJoueurs(){
		return joueurs;
	}
	

	public int getIndexJoueurAdverse() {
		return indexJoueurAdverse;
	}

	/**
	 * 
	 * @return Retourne le joueur actuel
	 */
	public Joueur getJoueurActuel() {
		return joueurs[indexJoueurActuel];
	}
	/**
	 * Initialise al�atoirement le joueur actuel (pour le d�but de partie)
	 */
	public void initJoueurActuel() {
		this.indexJoueurActuel = ((int)(Math.random()*2));
		this.indexJoueurAdverse = (indexJoueurActuel+1)%2;
	}
	/**
	 * Permet de passer au joueur suivant
	 */
	public void joueurSuivant() {
		indexJoueurAdverse = indexJoueurActuel;
		indexJoueurActuel = (indexJoueurActuel+1)%2;
        for (JoueurListener l : joueurListener) {
            l.changeJoueur(getJoueur(indexJoueurActuel));
        }
	}
	/**
	 * 
	 * @return Le joueur adverse
	 */
	public Joueur getJoueurAdverse() {
		return joueurs[indexJoueurAdverse];
	}
	
	public int getIndexJoueurActuel() {
		return indexJoueurActuel;
	}
	@Override
	public String toString() {
		String rtrString = "";
		for (int i = 0;i<joueurs.length;i++) {
			rtrString += "- "+joueurs[i]+"\n";
		}
		return rtrString;
	}
}

