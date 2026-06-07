package com.mygdx.game;

public class PasarNivel extends SecuenciaEvento{
	@Override
	protected void reproducirEfectoVisual() {
		System.out.println("Pasaste de Nivel!");
	}
	@Override 
	
	protected void actualizarPuntajes0Vidas(ManejoDePuntaje pv) {
		pv.sumarPuntos(100);
	}
	@Override 
	protected void reiniciarPOsiciones(BlockBreakerGame juego) {
		super.reiniciarPosiciones(juego);
		juego.subirNivel();
	}

}
