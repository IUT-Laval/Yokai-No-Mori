package com.laval.iut.yokainomori;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public abstract class Jeu {

	private BidiMap<Case, Pion> gestionnairePion = new DualHashBidiMap<Case, Pion>();
	private GestionnaireJoueurs gestionnaireJoueur;
	private boolean termine = false;
	private Joueur vainqueur = null;
	private Plateau plateau;
	List<Deplacement> deplacementsJ1 = new ArrayList<Deplacement>();
	List<Deplacement> deplacementsJ2 = new ArrayList<Deplacement>();

	public Jeu() {
		initialiserJeu();
		deplacementsJ1.add(Deplacement.AVANT);
		deplacementsJ2.add(Deplacement.ARRIERE);

	}

	/**
	 *
	 * @param pion
	 *            Pion du joueur
	 * @param arrive
	 *            Case cible pour le pion
	 * @return
	 */
	public boolean jouer(Pion pion, Case arrive) {
		// controle si c'est un parachutage
		if (testParachutage(pion, arrive)) {
			gestionnaireJoueur.getJoueurActuel().getReserve().remove(pion);
			gestionnaireJoueur.getJoueurActuel().getPions().add(pion);
			parachuter(pion, arrive);
			gestionnaireJoueur.joueurSuivant();
			return true;
		}

		if (gestionnairePion.getKey(pion) == null)
			return false;

		// controle si l'arrivée n'est pas le départ
		if (gestionnairePion.getKey(pion).equals(arrive))
			return false;

		//si le plateau est un 5*6 et le pion sélectionné est un kodama
		if(plateau.getLargeur() == 5 && plateau.getHauteur() == 6 )

			if (pion.getDeplacements().equals(deplacementsJ1) || pion.getDeplacements().equals(deplacementsJ2) ){
				for (int i = 0; i <= 6; i++) {
					//si une des cases contient un kodama on annule le déplacement
					if(gestionnairePion.get(plateau.getCase(arrive.getX(), i)).getDeplacements().equals(deplacementsJ1) ||
							gestionnairePion.get(plateau.getCase(arrive.getX(), i)).getDeplacements().equals(deplacementsJ2)){
						System.out.println("Impossible d'avoir deux kodama de votre équipe sur la même colonne");
						return false;
					}
				}
			}

		// controle si le pion appartient au joueur
		if (!gestionnaireJoueur.getJoueurActuel().getPions().contains(pion))
			return false;
		if (gestionnairePion.get(arrive) != null)
			if (gestionnaireJoueur.getJoueurActuel().getPions().contains(gestionnairePion.get(arrive)))
				return false;
		// controle si le déplacement est valide pour ce pion
		int x = gestionnairePion.getKey(pion).getX() - arrive.getX();
		int y = gestionnairePion.getKey(pion).getY() - arrive.getY();
		for (Deplacement deplacement : pion.getDeplacements()) {
			if (deplacement.getX() == -x && deplacement.getY() == -y) {
				// si un pion ce trouve sur la case cible on capture
				if (gestionnairePion.get(arrive) != null) {
					capturer(gestionnairePion.get(arrive));
				}
				// déplacer le pion
				deplacer(pion, arrive);
				gestionnairePion.put(arrive, pion);
				// promotion si le pion qui a bouger est évoluable et rentre
				// dans la zone
				//si il peut pas bouger ensuite, promotion obligatoire, sinon demande promotion
				if (gestionnaireJoueur.getJoueurActuel().getZonePromotion().contains(arrive)
						&& pion instanceof PionEvoluable) {
					if (testMouvement(pion, arrive)){
						PionEvoluable pionE = (PionEvoluable) pion;
						pionE.evoluer();
					}
					//demande evolution
					else{
						if(demandeEvlution()==true){
							PionEvoluable pionE = (PionEvoluable) pion;
							pionE.evoluer();
						}
					}
				}
				gestionnaireJoueur.joueurSuivant();
				return true;
			}
		}
		return false;
	}


	// TODO : affichage d'un message dans l'iu => besoin de l'acces au
	// controller ou view
	private boolean demandeEvlution() {
		return true;
	}

	public void deplacer(Pion pion, Case arrive) {
		gestionnairePion.put(arrive, pion);
	}

	public void parachuter(Pion pion, Case arrive) {
		gestionnairePion.put(arrive, pion);
	}

	public boolean testParachutage(Pion pion, Case arrive) {
		return (gestionnaireJoueur.getJoueurActuel().getReserve().contains(pion)
				&& gestionnairePion.get(arrive) == null);
	}

	public void capturer(Pion pion) {
		if (pion.isImportant()) {
			termine = true;
			vainqueur = gestionnaireJoueur.getJoueurActuel();
		} else {
			gestionnaireJoueur.getJoueurAdverse().getPions().remove(pion);
			gestionnaireJoueur.getJoueurActuel().getReserve().add(pion);
			pion.seRetourner();
			if (pion instanceof PionEvoluable)
				((PionEvoluable) pion).desevoluer();
		}
	}

	public boolean testMouvement(Pion pion, Case arrive) {
		for (Deplacement deplacement : pion.getDeplacements()) {
			if (arrive.getX() + deplacement.getX() >= 0
					&& arrive.getX() + deplacement.getX() < getPlateau().getLargeur()
					&& arrive.getY() + deplacement.getY() >= 0
					&& arrive.getY() + deplacement.getY() < getPlateau().getHauteur())
				return true;
		}
		return false;
	}

	public BidiMap<Case, Pion> getGestionnairePion() {
		return gestionnairePion;
	}

	public GestionnaireJoueurs getGestionnaireJoueur() {
		return gestionnaireJoueur;
	}

	public Joueur getVainqueur() {
		return vainqueur;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public boolean isTermine() {
		return termine;
	}

	public void setTermine(boolean termine) {
		this.termine = termine;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}

	public void setGestionnaireJoueur(GestionnaireJoueurs gestionnaireJoueur) {
		this.gestionnaireJoueur = gestionnaireJoueur;
	}

	/**
	 * Initialise les joueurs, le plateau avec ses cases et les pions.
	 */
	// ici les pion des pions evoluable ne devrai pas etre referencer pour pas
	// de double pr�sence
	public abstract void initialiserJeu();

}
