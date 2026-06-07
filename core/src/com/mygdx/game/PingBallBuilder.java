package com.mygdx.game;

public class PingBallBuilder {
	private int x;
	private int y;
	private int size;
	private int xSpeed;
	private int ySpeed;
	private boolean iniciaQuieto;
	
	public PingBallBuilder() {
		this.size=10;
		this.xSpeed=5;
		this.ySpeed=7;
		this.iniciaQuieto=true;
	}
	
	public PingBallBuilder setPosicion(int x,int y) {
		this.x=x;
		this.y=y;
		return this;
	}
	
	public PingBallBuilder setSize(int size) {
		this.size=size;
		return this;
	}
	
	public PingBallBuilder setVelocidad(int xSpeed, int ySpeed) {
		this.xSpeed=xSpeed;
		this.ySpeed=ySpeed;
		return this;
	}
	
	public PingBallBuilder setInicioQuieto(boolean iniciaQuieto) {
		this.iniciaQuieto=iniciaQuieto;
		return this;
	}
	
	public PingBall build() {
		return new PingBall(x,y,size,xSpeed,ySpeed,iniciaQuieto);
	}
	

}
