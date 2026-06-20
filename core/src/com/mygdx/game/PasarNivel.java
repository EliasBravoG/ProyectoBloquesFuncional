package com.mygdx.game;

public class PasarNivel extends SecuenciaEvento{
	@Override
	protected void reproducirEfectoVisual(BlockBreakerGame juego) {
		System.out.println("Pasaste al Nivel " + juego.getNivel());
	}
	@Override 
	
	protected void actualizarPuntajes0Vidas(ManejoDePuntaje pv) {
		pv.sumarPuntos(100);
	}
	@Override 
	protected void reiniciarPosiciones(BlockBreakerGame juego) {
		super.reiniciarPosiciones(juego);
		juego.subirNivel();
	}

}
