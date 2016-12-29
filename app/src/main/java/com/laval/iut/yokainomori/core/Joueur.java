package com.laval.iut.yokainomori.core;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	
	private String nom;
	//liste de pion sur le plateau appartenant au joueur
	private List<Pion> pions = new ArrayList<Pion>();
	private List<Pion> reserve = new ArrayList<Pion>();
	private List<Case> zonePromotion = new ArrayList<Case>();
	public Joueur(String nom) {
		super();
		this.nom = nom;
	} 
	
	public List<Pion> getPions() {
		return pions;
	}
	public void setPions(List<Pion> pions) {
		this.pions = pions;
	}
	public List<Pion> getReserve() {
		return reserve;
	}
	public void setReserve(List<Pion> reserve) {
		this.reserve = reserve;
	}
	public List<Case> getZonePromotion() {
		return zonePromotion;
	}
	public void setZonePromotion(List<Case> zonePromotion) {
		this.zonePromotion = zonePromotion;
	}

	public String getNom() {
		return nom;
	}

	@Override
	public String toString() {
		return this.nom;
	}


}
