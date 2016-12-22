package com.laval.iut.yokainomori.core;

import com.laval.iut.yokainomori.R;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Nicolas on 28/09/2016.
 */

public class Pion {
	
	private String nom;
	private List<Deplacement> deplacements = new ArrayList<Deplacement>();
	private int img = R.drawable.empty;
	private boolean important = false;

	public Pion(String nom, int img, List<Deplacement> deplacements) {
		this.nom = nom;
		this.deplacements = deplacements;
		this.img = img;
	}

	@Override
	public String toString() {
		return getNom();
	}
 // pb ici
	public void seRetourner() {
		ListIterator<Deplacement> i = this.deplacements.listIterator();
		Deplacement deplacement;
		while (i.hasNext()){
			deplacement = i.next();
			switch (deplacement) {
			case AVANT:
				i.set(Deplacement.ARRIERE);
				break;
			case ARRIERE:
				i.set(Deplacement.AVANT);;
				break;
			case DROITE:
				i.set(Deplacement.GAUCHE);;
				break;
			case GAUCHE:
				i.set(Deplacement.DROITE);
				break;
			case AVANT_DROITE:
				i.set(Deplacement.ARRIERE_GAUCHE);
				break;
			case AVANT_GAUCHE:
				i.set(Deplacement.ARRIERE_DROITE);
				break;
			case ARRIERE_DROITE:
				i.set(Deplacement.AVANT_GAUCHE);
				break;
			case ARRIERE_GAUCHE:
				i.set(Deplacement.AVANT_DROITE);
				break;
			default:
				break;
			}
		}
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Deplacement> getDeplacements() {
		return deplacements;
	}
	public void setDeplacements(List<Deplacement> deplacements) {
		this.deplacements = deplacements;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public boolean isImportant() {
		return important;
	}
	public void setImportant(boolean important) {
		this.important = important;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	
}
