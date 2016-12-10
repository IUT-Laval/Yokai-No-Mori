package com.laval.iut.yokainomori.core;

/**
 * Created by Nicolas on 28/09/2016.
 */

public enum Deplacement {
	
    AVANT(0,1),
    ARRIERE(0,-1),
    DROITE(1,0),
    GAUCHE(-1,0),
    AVANT_DROITE(1,1),
    AVANT_GAUCHE(-1,1),
    ARRIERE_DROITE(1,-1),
    ARRIERE_GAUCHE(-1,-1);
	
    private int x;
    private int y;
    
    Deplacement(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
//    @Override
//    public String toString(){
//    	return "["+x+","+y+"]";
//    }
    
}
