package com.mygdx.game;

public class ManejoDePuntaje {
	private static ManejoDePuntaje instance;
	
	private int vidas;
	private int puntaje;
	
	private ManejoDePuntaje() {
		reset();
	}
	
	public static ManejoDePuntaje getInstance() {
		if(instance==null) {
			instance= new ManejoDePuntaje();
		}
		return instance;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	public void perderVida() {
		vidas--;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public void sumerPuntos(int puntos) {
		puntaje+=puntos;
	}
	
	public void reset() {
		vidas=3;
		puntaje=0;
	}

}
