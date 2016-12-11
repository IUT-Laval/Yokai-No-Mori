package com.laval.iut.yokainomori.core;

import java.util.Scanner;

public class MAIN {
	public static Jeu jeu = new Jeu34();
	public static int reponseX, reponseY;
	public static String reponsePionString;
	public static Pion reponsePion;
	public static String plateau;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("JEU DU YOKAI NO MORI\n");

		while (!jeu.isTermine()) {
			System.out.println("TOUR DU JOUEUR " + jeu.getGestionnaireJoueur().getJoueurActuel() + "\n");

			afficherPlateau();
			do {
				System.out.println("Quel pion voulez vous d�placer ? (de votre r�serve ou du plateau)");
				sc = new Scanner(System.in);
				reponsePionString = sc.nextLine();
			} while (!testPion());
			System.out.println(reponsePion.getDeplacements());

			System.out.println("Quelles coordon�es du plateau ?\n");
			System.out.println("x ?");
			sc = new Scanner(System.in);
			reponseX = sc.nextInt();
			System.out.println("y ?");
			sc = new Scanner(System.in);
			reponseY = sc.nextInt();

			jeu.jouer(reponsePion, jeu.getPlateau().getCases()[reponseX][reponseY]);

		}
		if (jeu.getVainqueur() == null)
			System.out.println("Match nul");
		else {
			System.out.println("Bravo au " + jeu.getVainqueur() + ", vainqueur de cette partie !");
		}

	}

	public static boolean testPion() {
		Pion pion = new Pion(reponsePionString, 0, null);
		int index;
		for (Joueur joueur : jeu.getGestionnaireJoueur().getJoueurs()) {
			if (joueur.getReserve().contains(pion)) {
				index = joueur.getReserve().indexOf(pion);
				reponsePion = joueur.getReserve().get(index);
				return true;
			}
			if (joueur.getPions().contains(pion)) {
				index = joueur.getPions().indexOf(pion);
				reponsePion = joueur.getPions().get(index);
				return true;
			}
		}
		return false;
	}

	public static void afficherPlateau() {
		System.out.println("Pion joueur 2 : " + jeu.getGestionnaireJoueur().getJoueur(1).getPions() + "\n"
				+ "R�serve joueur 2 :" + jeu.getGestionnaireJoueur().getJoueur(1).getReserve() + "\n" + getPlateau()
				+ "R�serve joueur 1 :" + jeu.getGestionnaireJoueur().getJoueur(0).getReserve() + "\n"
				+ "Pion joueur 1 : " + jeu.getGestionnaireJoueur().getJoueur(0).getPions() + "\n");

	}
	// commenter la bonne partie en fonction du jeu
	public static String getPlateau(){
		return " ||        ||        ||        ||\n"
			 + "3|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][3])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][3])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][3])+"  ||\n"
			 + " ||        ||        ||        ||\n"
			 + " ||________||________||________||\n"
			 + " ||        ||        ||        ||\n"
			 + "2|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][2])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][2])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][2])+"  ||\n"
			 + " ||        ||        ||        ||\n"
			 + " ||________||________||________||\n"
			 + " ||        ||        ||        ||\n"
			 + "1|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][1])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][1])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][1])+"  ||\n"
			 + " ||        ||        ||        ||\n"
			 + " ||________||________||________||\n"
			 + " ||        ||        ||        ||\n"
			 + "0|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][0])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][0])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][0])+"  ||\n"
	         + " ||        ||        ||        ||\n"
	         + "Y||________||________||________||\n"
	         + "  X   0         1         2\n";
//		return " ||        ||        ||        ||        ||        ||\n"
//		 + "5|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][5])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][5])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][5])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[3][5])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[4][5])+"  ||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + " ||________||________||________||________||________||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + "4|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][4])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][4])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][4])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[3][4])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[4][4])+"  ||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + " ||________||________||________||________||________||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + "3|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][3])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][3])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][3])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[3][3])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[4][3])+"  ||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + " ||________||________||________||________||________||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + "2|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][2])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][2])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][2])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[3][2])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[4][2])+"  ||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + " ||________||________||________||________||________||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + "1|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][1])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][1])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][1])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[3][1])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[4][1])+"  ||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + " ||________||________||________||________||________||\n"
//		 + " ||        ||        ||        ||        ||        ||\n"
//		 + "0|| " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][0])+"   ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[1][0])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[2][0])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[3][0])+"  ||  " +jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[4][0])+"  ||\n"
//         + " ||        ||        ||        ||        ||        ||\n"
//         + "Y||________||________||________||________||________||\n"
//         + "  X   0         1         2         3         4\n";
	}

}
