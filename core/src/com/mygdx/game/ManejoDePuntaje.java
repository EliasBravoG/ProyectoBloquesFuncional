package com.mygdx.game;

public class ManejoDePuntaje {
	private static ManejoDePuntaje instance;
	
	private int vidas;
	private int puntaje;
	
	//atributo qeu almacena la estrategia 
	private StrategyPuntaje estrategiaActual;
	
	private ManejoDePuntaje() {
		reset();
	}
	
	public static ManejoDePuntaje getInstance() {
		if(instance==null) {
			instance= new ManejoDePuntaje();
		}
		return instance;
	}
	

	public void setEstrategia(StrategyPuntaje nuevaEstrategia) {
		this.estrategiaActual=nuevaEstrategia;
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
	
	public void sumarPuntos(int puntosBase) {
		int puntosFinales=estrategiaActual.calcularPuntos(puntosBase);
		puntaje+=puntosFinales;
	}
	
	public void reset() {
		vidas=3;
		puntaje=0;
		
		
		estrategiaActual=new PuntajeNormal();
	}

}
