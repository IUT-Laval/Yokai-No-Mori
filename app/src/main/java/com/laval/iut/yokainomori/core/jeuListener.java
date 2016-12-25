package com.laval.iut.yokainomori.core;

/**
 * Created by lione on 10/12/2016.
 */

public interface JeuListener {

    public void init();

    public void deplacePion(Pion pion);

    public void capturePion(Pion pion, String nomJoueur);

    public void finPartie(int gagnant);

    public void demandeEvolutionPion(PionEvoluable pion);

    public void evoluePion(Pion pion);

}
