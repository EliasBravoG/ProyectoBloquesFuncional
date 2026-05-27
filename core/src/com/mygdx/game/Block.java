package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block {
    //int x,y,width,height;
	
	//encapsulamiento ponermos los atrivutos en privado
	private int x;
	private int y;
	private int width;
	private int height;
	private Color cc;
	private boolean destroyed;
	
    //Color cc;
    //boolean destroyed;
    
    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
        Random r = new Random(x+y);
        //cc = Color.RED;
        cc = new Color(255, 0, 0,0);
       //cc = new Color(0.1f+r.nextFloat(0.9f), r.nextFloat(0.9f), r.nextFloat(0.9f), 10);
  
    }
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
    
    //metodos de acceso publico es decir getters y setters
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
    
    public boolean isDestroyed() {
    	return destroyed;
    }
    
    public void setDestroyed(boolean destroyed) {
    	this.destroyed=destroyed;
    }
}
