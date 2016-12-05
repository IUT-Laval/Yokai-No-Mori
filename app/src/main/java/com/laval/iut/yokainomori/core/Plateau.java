package com.laval.iut.yokainomori.core;

public class Plateau {

	private int largeur;
	private int hauteur;
	private Case[][] cases;

	public Plateau(int largeur, int hauteur) {
		super();
		this.largeur = largeur;
		this.hauteur = hauteur;
		cases = new Case[largeur][hauteur];
		for(int y = 0;y < hauteur ; y++){
			for (int x = 0; x < largeur ; x++) {
				cases[x][y] = new Case(x,y);
			}
			
		}
	}
	
	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public Case[][] getCases() {
		return cases;
	}
}
