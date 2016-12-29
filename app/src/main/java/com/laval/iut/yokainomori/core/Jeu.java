package com.laval.iut.yokainomori.core;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.ArrayList;
import java.util.List;

public abstract class Jeu {

	private BidiMap<Case, Pion> gestionnairePion = new DualHashBidiMap<Case, Pion>();
	private GestionnaireJoueurs gestionnaireJoueur;
	private boolean termine = false;
	private Joueur vainqueur = null;
	private Plateau plateau;

	private List<JeuListener> jeuListener = new ArrayList<>();

    public void addJeuListeners(JeuListener l) {
        jeuListener.add(l);
    }


	public Jeu() {
		initialiserJeu();
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
		// controle si le l'arriv�e n'est pas le d�part
		if (gestionnairePion.getKey(pion) == null)
			return false;
		if (gestionnairePion.getKey(pion).equals(arrive))
			return false;
		// controle si le pion appartien au joueur
		if (!gestionnaireJoueur.getJoueurActuel().getPions().contains(pion))
			return false;
		if (gestionnairePion.get(arrive) != null)
			if (gestionnaireJoueur.getJoueurActuel().getPions().contains(gestionnairePion.get(arrive)))
				return false;
		// controle si le d�placement est valide pour ce pion
		int x = gestionnairePion.getKey(pion).getX() - arrive.getX();
		int y = gestionnairePion.getKey(pion).getY() - arrive.getY();
		for (Deplacement deplacement : pion.getDeplacements()) {
			if (deplacement.getX() == -x && deplacement.getY() == -y) {
				// si un pion ce trouve sur la case cible on capture
				if (gestionnairePion.get(arrive) != null) {
					capturer(gestionnairePion.get(arrive));
				}

				// origine pour verifier la sortie d'une zone
				Case origine = getGestionnairePion().getKey(pion);
				// d�placer le pion
				deplacer(pion, arrive);
				gestionnairePion.put(arrive, pion);
				// promotion si le pion qui a bouger est �voluable et rentre
				// dans la zone, ou en sort
				//si il peut pas bouger ensuite, promotion obligatoire, sinon demande promotion
				if ((gestionnaireJoueur.getJoueurActuel().getZonePromotion().contains(arrive) || gestionnaireJoueur.getJoueurActuel().getZonePromotion().contains(origine))
						&& pion instanceof PionEvoluable && ((PionEvoluable) pion).isEvolue()==false) {
					PionEvoluable pionE = (PionEvoluable) pion;
					if (!testMouvement(pion, arrive)){
						pionE.evoluer();
						for (JeuListener l : jeuListener) {
							l.evoluePion(pion);
						}
						gestionnaireJoueur.joueurSuivant();
					}
					//demande evolution
					else{
						for (JeuListener l : jeuListener) {
							// attention, le gestion du joueur suivant se fait ici coté vue :
							// on attend reponse de l'utilisateur avant de passer au joueur suivant.
							l.demandeEvolutionPion(pionE);
						}
					}
				}
				else {
					gestionnaireJoueur.joueurSuivant();
				}
				return true;
			}
		}
		return false;
	}

	public void deplacer(Pion pion, Case arrive) {
		gestionnairePion.put(arrive, pion);
        for (JeuListener l : jeuListener) {
            l.deplacePion(pion);
        }
	}

	public void parachuter(Pion pion, Case arrive) {
        deplacer(pion, arrive);
	}

	public boolean testParachutage(Pion pion, Case arrive) {
		return (gestionnaireJoueur.getJoueurActuel().getReserve().contains(pion)
				&& gestionnairePion.get(arrive) == null);
	}

	public void capturer(Pion pion) {
		if (pion.isImportant()) {
			termine = true;
			vainqueur = gestionnaireJoueur.getJoueurActuel();
            for (JeuListener l : jeuListener) {
                l.finPartie(gestionnaireJoueur.getIndexJoueurActuel());
            }
		}
		gestionnaireJoueur.getJoueurAdverse().getPions().remove(pion);
		gestionnaireJoueur.getJoueurActuel().getReserve().add(pion);
		pion.seRetourner();
		if (pion instanceof PionEvoluable)
				((PionEvoluable) pion).desevoluer();
        for (JeuListener l : jeuListener) {
            l.capturePion(pion, gestionnaireJoueur.getJoueurActuel().getNom());
        }
	}
	public boolean testMouvement(Pion pion, Case arrive) {
		for(Deplacement deplacement : pion.getDeplacements()){
			if(arrive.getX() + deplacement.getX() >=0 && arrive.getX() + deplacement.getX()<getPlateau().getLargeur() && arrive.getY() + deplacement.getY() >=0 && arrive.getY() + deplacement.getY()<getPlateau().getHauteur())
				return true;
		}
		return false;
	}

	public List<JeuListener> getJeuListener() {
		return jeuListener;
	}

	public void matchNul(){
		termine = true;
		vainqueur = null;

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
	// ici les pion des pions evoluable ne devrai pas etre referencer pour pas de double pr�sence
	public void initialiserJeu() {
        for (JeuListener l : jeuListener) {
            l.init();
        }
    }

    public boolean isRetourne(Pion pion) {
        return (gestionnaireJoueur.getJoueur(1).getReserve().contains(pion) || gestionnaireJoueur.getJoueur(1).getPions().contains(pion));
    }

}
