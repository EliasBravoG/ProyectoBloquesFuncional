//se agrega una clase abstracta para agrupar las variables fundamentales qeu comparten lo selemntos del jueog 

package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class gameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public gameObject(int x,int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public abstract void draw(ShapeRenderer shape);

}
