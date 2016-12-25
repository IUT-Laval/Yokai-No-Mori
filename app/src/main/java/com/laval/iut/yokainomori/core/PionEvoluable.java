
package com.laval.iut.yokainomori.core;


import java.util.List;

/**
 * Created by Nicolas on 28/09/2016.
 */

public class PionEvoluable extends Pion implements Evoluable {
	
	private Pion evolution;
	private boolean isEvolue;

	public PionEvoluable(String nom, int img, List<Deplacement> deplacements, Pion evolution) {
		super(nom, img, deplacements);
		this.evolution = evolution;
		this.isEvolue = false;
	}

	@Override
	public void seRetourner() {
		evolution.seRetourner();
		super.seRetourner();
	}

	@Override
	public String getNom() {
		if (isEvolue)
			return evolution.getNom();
		else return super.getNom();
		
	}

	@Override
	public List<Deplacement> getDeplacements() {
		if(!isEvolue)
			return super.getDeplacements();
		else return evolution.getDeplacements();
	}

	@Override
	public int getImg() {
		if (!isEvolue)
			return super.getImg();
		else return evolution.getImg();
	}

	public Pion getEvolution() {
		return evolution;
	}

	public void evoluer(){
    	isEvolue = true;

    }
    public void desevoluer(){
    	isEvolue = false;
    }


	public boolean isEvolue() {
		return isEvolue;
	}
}
