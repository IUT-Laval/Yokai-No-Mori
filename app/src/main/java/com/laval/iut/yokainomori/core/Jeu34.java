package com.laval.iut.yokainomori.core;

import java.util.ArrayList;
import java.util.List;

public class Jeu34 extends Jeu {
	private void controlerImportance() {
		int y;
		if (getGestionnaireJoueur().getIndexJoueurActuel() == 0)
			y = 0;
		else
			y = 3;
		for (int i = 0; i < 3; i++)
			if (getGestionnairePion().get(getPlateau().getCases()[i][y]) != null)
				if (getGestionnairePion().get(getPlateau().getCases()[i][y]).isImportant()
						&& getGestionnaireJoueur().getJoueurAdverse().getPions()
								.contains(getGestionnairePion().get(getPlateau().getCases()[i][y]))) {
					setTermine(true);
					setVainqueur(getGestionnaireJoueur().getJoueurAdverse());
				}

	}

	@Override
	public void deplacer(Pion pion, Case arrive) {
		super.deplacer(pion, arrive);
		controlerImportance();
	}

	@Override
	public void parachuter(Pion pion, Case arrive) {
		super.parachuter(pion, arrive);
		controlerImportance();
	}

	public void initialiserJeu() {

		setGestionnaireJoueur(new GestionnaireJoueurs("J1", "J2"));
		getGestionnaireJoueur().initJoueurActuel();

		setPlateau(new Plateau(3, 4));
		Case cases[][] = getPlateau().getCases();
		getGestionnaireJoueur().getJoueur(0).getZonePromotion().add(cases[0][3]);
		getGestionnaireJoueur().getJoueur(0).getZonePromotion().add(cases[1][3]);
		getGestionnaireJoueur().getJoueur(0).getZonePromotion().add(cases[2][3]);
		getGestionnaireJoueur().getJoueur(1).getZonePromotion().add(cases[0][0]);
		getGestionnaireJoueur().getJoueur(1).getZonePromotion().add(cases[1][0]);
		getGestionnaireJoueur().getJoueur(1).getZonePromotion().add(cases[2][0]);

		List<Deplacement> deplacementKoropokkuru, deplacementKitsune, deplacementTanuki, deplacementKodama,
				deplacementKodamaSamurai;

		deplacementKoropokkuru = new ArrayList<Deplacement>();
		deplacementKitsune = new ArrayList<Deplacement>();
		deplacementTanuki = new ArrayList<Deplacement>();
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

		deplacementKitsune.add(Deplacement.ARRIERE_DROITE);
		deplacementKitsune.add(Deplacement.ARRIERE_GAUCHE);
		deplacementKitsune.add(Deplacement.AVANT_DROITE);
		deplacementKitsune.add(Deplacement.AVANT_GAUCHE);

		deplacementTanuki.add(Deplacement.ARRIERE);
		deplacementTanuki.add(Deplacement.AVANT);
		deplacementTanuki.add(Deplacement.DROITE);
		deplacementTanuki.add(Deplacement.GAUCHE);

		deplacementKodama.add(Deplacement.AVANT);

		deplacementKodamaSamurai.add(Deplacement.AVANT);
		deplacementKodamaSamurai.add(Deplacement.AVANT_DROITE);
		deplacementKodamaSamurai.add(Deplacement.AVANT_GAUCHE);
		deplacementKodamaSamurai.add(Deplacement.DROITE);
		deplacementKodamaSamurai.add(Deplacement.GAUCHE);
		deplacementKodamaSamurai.add(Deplacement.ARRIERE);

		Pion pionKoropokkuru1 = new Pion("Kor1", "", new ArrayList<Deplacement>(deplacementKoropokkuru));
		Pion pionKitsune1 = new Pion("Kit1", "", new ArrayList<Deplacement>(deplacementKitsune));
		Pion pionTanuki1 = new Pion("Tan1", "", new ArrayList<Deplacement>(deplacementTanuki));
		Pion pionKodamaSamurai1 = new Pion("KoS1", "", new ArrayList<Deplacement>(deplacementKodamaSamurai));
		PionEvoluable pionKodama1 = new PionEvoluable("Kod1", "", new ArrayList<Deplacement>(deplacementKodama),
				pionKodamaSamurai1);

		Pion pionKoropokkuru2 = new Pion("Kor2", "", new ArrayList<Deplacement>(deplacementKoropokkuru));
		Pion pionKitsune2 = new Pion("Kit2", "", new ArrayList<Deplacement>(deplacementKitsune));
		Pion pionTanuki2 = new Pion("Tan2", "", new ArrayList<Deplacement>(deplacementTanuki));
		Pion pionKodamaSamurai2 = new Pion("KoS2", "", new ArrayList<Deplacement>(deplacementKodamaSamurai));
		PionEvoluable pionKodama2 = new PionEvoluable("Kod2", "", new ArrayList<Deplacement>(deplacementKodama),
				pionKodamaSamurai2);

		// mise en place des piece importante (d�faite si captur�e ou autre)
		pionKoropokkuru1.setImportant(true);
		pionKoropokkuru2.setImportant(true);

		getGestionnairePion().put(getPlateau().getCases()[1][0], pionKoropokkuru1); // Koropokkuru
		getGestionnairePion().put(getPlateau().getCases()[0][0], pionKitsune1); // Kitsune
		getGestionnairePion().put(getPlateau().getCases()[2][0], pionTanuki1); // Tanuki
		getGestionnairePion().put(getPlateau().getCases()[1][1], pionKodama1); // Kodama

		getGestionnairePion().put(getPlateau().getCases()[1][3], pionKoropokkuru2); // Koropokkuru
		getGestionnairePion().put(getPlateau().getCases()[2][3], pionKitsune2); // Kitsune
		getGestionnairePion().put(getPlateau().getCases()[0][3], pionTanuki2); // Tanuki
		getGestionnairePion().put(getPlateau().getCases()[1][2], pionKodama2); // Kodama

		List<Pion> pionsJ1 = getGestionnaireJoueur().getJoueur(0).getPions();
		List<Pion> pionsJ2 = getGestionnaireJoueur().getJoueur(1).getPions();

		pionsJ1.add(pionKoropokkuru1);
		pionsJ2.add(pionKoropokkuru2);
		pionsJ1.add(pionKitsune1);
		pionsJ2.add(pionKitsune2);
		pionsJ1.add(pionTanuki1);
		pionsJ2.add(pionTanuki2);
		pionsJ1.add(pionKodama1);
		pionsJ2.add(pionKodama2);
		for (Pion pion : pionsJ2) {
			pion.seRetourner();
		}
	}

}
