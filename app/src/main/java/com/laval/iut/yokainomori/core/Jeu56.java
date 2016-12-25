package com.laval.iut.yokainomori.core;

import com.laval.iut.yokainomori.R;

import java.util.ArrayList;
import java.util.List;

public class Jeu56 extends Jeu {
	// Le test pour cette version, verifie que le pion parachuter peut se
	// d�placer apr�s son parachutage
	@Override
	public boolean testParachutage(Pion pion, Case arrive) {
		if (super.testParachutage(pion, arrive)) {
            // on verifie la règle des kodoma sur la meme colone
            if (pion.getNom().equals("kodama")) {
                for (int i = 0; i < getPlateau().getHauteur(); i++) {
                    if (getGestionnairePion().get(getPlateau().getCases()[arrive.getX()][i]) != null &&
                            getGestionnairePion().get(getPlateau().getCases()[arrive.getX()][i]).getNom().equals("kodama")
                            && getGestionnaireJoueur().getJoueurActuel().getPions().contains(getGestionnairePion().get(getPlateau().getCases()[arrive.getX()][i])))
                        return false;
                }
            }
            return testMouvement(pion, arrive);
        }
		return false;
	}

	@Override
	public void initialiserJeu() {
		{

			setGestionnaireJoueur(new GestionnaireJoueurs("J1", "J2"));
			getGestionnaireJoueur().initJoueurActuel();

			setPlateau(new Plateau(5, 6));
			Case[][] cases = getPlateau().getCases();
			for (int i = 0; i < 5; i++) {
				getGestionnaireJoueur().getJoueur(0).getZonePromotion().add(cases[i][5]);
				getGestionnaireJoueur().getJoueur(0).getZonePromotion().add(cases[i][4]);
			}
			for (int i = 0; i < 5; i++) {
				getGestionnaireJoueur().getJoueur(1).getZonePromotion().add(cases[i][0]);
				getGestionnaireJoueur().getJoueur(1).getZonePromotion().add(cases[i][1]);
			}

			List<Deplacement> deplacementKoropokkuru, deplacementKirin, deplacementOni, deplacementSuperOni,
					deplacementKodama, deplacementKodamaSamurai;

			deplacementKoropokkuru = new ArrayList<Deplacement>();
			deplacementKirin = new ArrayList<Deplacement>();
			deplacementOni = new ArrayList<Deplacement>();
			deplacementSuperOni = new ArrayList<Deplacement>();
			deplacementKodama = new ArrayList<Deplacement>();
			deplacementKodamaSamurai = new ArrayList<Deplacement>();

			deplacementKoropokkuru.add(Deplacement.ARRIERE);
			deplacementKoropokkuru.add(Deplacement.ARRIERE_DROITE);
			deplacementKoropokkuru.add(Deplacement.ARRIERE_GAUCHE);
			deplacementKoropokkuru.add(Deplacement.AVANT);
			deplacementKoropokkuru.add(Deplacement.AVANT_DROITE);
			deplacementKoropokkuru.add(Deplacement.AVANT_GAUCHE);
			deplacementKoropokkuru.add(Deplacement.DROITE);
			deplacementKoropokkuru.add(Deplacement.GAUCHE);

			deplacementOni.add(Deplacement.AVANT);
			deplacementOni.add(Deplacement.AVANT_DROITE);
			deplacementOni.add(Deplacement.AVANT_GAUCHE);
			deplacementOni.add(Deplacement.ARRIERE_DROITE);
			deplacementOni.add(Deplacement.ARRIERE_GAUCHE);

			deplacementSuperOni.add(Deplacement.AVANT);
			deplacementSuperOni.add(Deplacement.AVANT_DROITE);
			deplacementSuperOni.add(Deplacement.AVANT_GAUCHE);
			deplacementSuperOni.add(Deplacement.DROITE);
			deplacementSuperOni.add(Deplacement.GAUCHE);
			deplacementSuperOni.add(Deplacement.ARRIERE);

			deplacementKirin.add(Deplacement.AVANT);
			deplacementKirin.add(Deplacement.AVANT_DROITE);
			deplacementKirin.add(Deplacement.AVANT_GAUCHE);
			deplacementKirin.add(Deplacement.DROITE);
			deplacementKirin.add(Deplacement.GAUCHE);
			deplacementKirin.add(Deplacement.ARRIERE);

			deplacementKodama.add(Deplacement.AVANT);

			deplacementKodamaSamurai.add(Deplacement.AVANT);
			deplacementKodamaSamurai.add(Deplacement.AVANT_DROITE);
			deplacementKodamaSamurai.add(Deplacement.AVANT_GAUCHE);
			deplacementKodamaSamurai.add(Deplacement.DROITE);
			deplacementKodamaSamurai.add(Deplacement.GAUCHE);
			deplacementKodamaSamurai.add(Deplacement.ARRIERE);
			//TODO : ajouter Oni en png avec le bon nom
			Pion pionKoropokkuru1 = new Pion("koroppokuru", R.drawable.koroppokuru, new ArrayList<Deplacement>(deplacementKoropokkuru));
			Pion pionKodamaSamurai1 = new Pion("kodama_samurai", R.drawable.kodama_samurai, new ArrayList<Deplacement>(deplacementKodamaSamurai));
			PionEvoluable pionKodama1 = new PionEvoluable("kodama", R.drawable.kodama, new ArrayList<Deplacement>(deplacementKodama),
					pionKodamaSamurai1);
			Pion pionKodamaSamurai2 = new Pion("kodama_samurai", R.drawable.kodama_samurai, new ArrayList<Deplacement>(deplacementKodamaSamurai));
			PionEvoluable pionKodama2 = new PionEvoluable("kodama", R.drawable.kodama, new ArrayList<Deplacement>(deplacementKodama),
					pionKodamaSamurai2);
			Pion pionKodamaSamurai3 = new Pion("kodama_samurai", R.drawable.kodama_samurai, new ArrayList<Deplacement>(deplacementKodamaSamurai));
			PionEvoluable pionKodama3 = new PionEvoluable("kodama", R.drawable.kodama, new ArrayList<Deplacement>(deplacementKodama),
					pionKodamaSamurai3);
			Pion pionSuperOni1 = new Pion("super_oni", R.drawable.super_oni, new ArrayList<Deplacement>(deplacementSuperOni));
			PionEvoluable pionOni1 = new PionEvoluable("oni", R.drawable.oni, new ArrayList<Deplacement>(deplacementOni), pionSuperOni1);
			Pion pionKirin1 = new Pion("kirin", R.drawable.kirin, new ArrayList<Deplacement>(deplacementKirin));
			Pion pionSuperOni2 = new Pion("super_oni", R.drawable.super_oni, new ArrayList<Deplacement>(deplacementSuperOni));
			PionEvoluable pionOni2 = new PionEvoluable("oni", R.drawable.oni, new ArrayList<Deplacement>(deplacementOni), pionSuperOni2);
			Pion pionKirin2 = new Pion("kirin", R.drawable.kirin, new ArrayList<Deplacement>(deplacementKirin));

			Pion pionKoropokkuru2 = new Pion("koroppokuru", R.drawable.koroppokuru, new ArrayList<Deplacement>(deplacementKoropokkuru));
			Pion pionKodamaSamurai4 = new Pion("kodama_samurai", R.drawable.kodama_samurai, new ArrayList<Deplacement>(deplacementKodamaSamurai));
			PionEvoluable pionKodama4 = new PionEvoluable("kodama", R.drawable.kodama, new ArrayList<Deplacement>(deplacementKodama),
					pionKodamaSamurai4);
			Pion pionKodamaSamurai5 = new Pion("kodama_samurai", R.drawable.kodama_samurai, new ArrayList<Deplacement>(deplacementKodamaSamurai));
			PionEvoluable pionKodama5 = new PionEvoluable("kodama", R.drawable.kodama, new ArrayList<Deplacement>(deplacementKodama),
					pionKodamaSamurai5);
			Pion pionKodamaSamurai6 = new Pion("kodama_samurai", R.drawable.kodama_samurai, new ArrayList<Deplacement>(deplacementKodamaSamurai));
			PionEvoluable pionKodama6 = new PionEvoluable("kodama", R.drawable.kodama, new ArrayList<Deplacement>(deplacementKodama),
					pionKodamaSamurai6);
			Pion pionSuperOni3 = new Pion("super_oni", R.drawable.super_oni, new ArrayList<Deplacement>(deplacementSuperOni));
			PionEvoluable pionOni3 = new PionEvoluable("oni", R.drawable.oni, new ArrayList<Deplacement>(deplacementOni), pionSuperOni3);
			Pion pionKirin3 = new Pion("kirin", R.drawable.kirin, new ArrayList<Deplacement>(deplacementKirin));
			Pion pionSuperOni4 = new Pion("super_oni", R.drawable.super_oni, new ArrayList<Deplacement>(deplacementSuperOni));
			PionEvoluable pionOni4 = new PionEvoluable("oni", R.drawable.oni, new ArrayList<Deplacement>(deplacementOni), pionSuperOni4);
			Pion pionKirin4 = new Pion("kirin", R.drawable.kirin, new ArrayList<Deplacement>(deplacementKirin));

			// mise en place des piece importante (d�faite si captur�e ou autre)
			pionKoropokkuru1.setImportant(true);
			pionKoropokkuru2.setImportant(true);

			getGestionnairePion().put(getPlateau().getCases()[2][0], pionKoropokkuru1); 
			getGestionnairePion().put(getPlateau().getCases()[0][0], pionOni1); 
			getGestionnairePion().put(getPlateau().getCases()[1][0], pionKirin1); 
			getGestionnairePion().put(getPlateau().getCases()[3][0], pionKirin2);
			getGestionnairePion().put(getPlateau().getCases()[4][0], pionOni2); 
			getGestionnairePion().put(getPlateau().getCases()[1][2], pionKodama1); 
			getGestionnairePion().put(getPlateau().getCases()[2][2], pionKodama2); 
			getGestionnairePion().put(getPlateau().getCases()[3][2], pionKodama3); 

			getGestionnairePion().put(getPlateau().getCases()[2][5], pionKoropokkuru2); 
			getGestionnairePion().put(getPlateau().getCases()[0][5], pionOni3); 
			getGestionnairePion().put(getPlateau().getCases()[1][5], pionKirin3); 
			getGestionnairePion().put(getPlateau().getCases()[3][5], pionKirin4);
			getGestionnairePion().put(getPlateau().getCases()[4][5], pionOni4); 
			getGestionnairePion().put(getPlateau().getCases()[1][3], pionKodama4); 
			getGestionnairePion().put(getPlateau().getCases()[2][3], pionKodama5); 
			getGestionnairePion().put(getPlateau().getCases()[3][3], pionKodama6); 

			List<Pion> pionsJ1 = getGestionnaireJoueur().getJoueur(0).getPions();
			List<Pion> pionsJ2 = getGestionnaireJoueur().getJoueur(1).getPions();

			pionsJ1.add(pionKoropokkuru1);
			pionsJ2.add(pionKoropokkuru2);
			pionsJ1.add(pionOni1);
			pionsJ1.add(pionOni2);
			pionsJ2.add(pionOni3);
			pionsJ2.add(pionOni4);
			pionsJ1.add(pionKirin1);
			pionsJ1.add(pionKirin2);
			pionsJ2.add(pionKirin3);
			pionsJ2.add(pionKirin4);
			pionsJ1.add(pionKodama1);
			pionsJ1.add(pionKodama2);
			pionsJ1.add(pionKodama3);
			pionsJ2.add(pionKodama4);
			pionsJ2.add(pionKodama5);
			pionsJ2.add(pionKodama6);
			for (Pion pion : pionsJ2) {
				pion.seRetourner();
			}

			super.initialiserJeu();
		}

	}

}
