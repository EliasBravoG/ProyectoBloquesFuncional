package com.mygdx.game;

public class SecuenciaPerderVida extends SecuenciaEvento{
	@Override
	protected void reproducirEfectoVisual() {
		System.out.println("Perdiste una vida!");
	}
	@Override
	protected void actualizarPuntajes0Vidas(ManejoDePuntaje pv) {
		pv.perderVida();
	}
	

}
