package com.laval.iut.yokainomori.core;

public class Case {
	
	private int x;
	private int y;
	
	public Case(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public String toString() {
		return "Case [x=" + x + ", y=" + y + "]";
	}
	
	
}
