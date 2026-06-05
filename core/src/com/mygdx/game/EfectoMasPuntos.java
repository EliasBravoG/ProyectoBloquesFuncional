package com.mygdx.game;

public class EfectoMasPuntos implements StrategyPuntaje {
	@Override
	public int calcularPuntos(int puntosBase) {
		return puntosBase*2; //multiplica los puntos por 2
	}
}
