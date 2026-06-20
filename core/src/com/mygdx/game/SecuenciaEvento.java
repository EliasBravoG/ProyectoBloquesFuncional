package com.mygdx.game;

public abstract class SecuenciaEvento {
	public final void ejecutarSecuencia(BlockBreakerGame juego, ManejoDePuntaje pv) {
		congelarMovimiento(juego);
		reproducirEfectoVisual(juego);
		actualizarPuntajes0Vidas(pv);
		reiniciarPosiciones(juego);
	}
	
	//pasos fijos 
	private void congelarMovimiento(BlockBreakerGame juego) {
		juego.getBall().setEstaQuieto(true);
	}
	
	
	protected void reiniciarPosiciones(BlockBreakerGame juego) {
		juego.reiniciarBola();
	}
	
	protected abstract void reproducirEfectoVisual(BlockBreakerGame juego);
	protected abstract void actualizarPuntajes0Vidas(ManejoDePuntaje pv);

}
