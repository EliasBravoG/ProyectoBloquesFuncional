package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

//import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block extends gameObject { //se agrega el "extends gameObject 
	private Color cc;
	private boolean destroyed;
	
    
    
    public Block(int x, int y, int width, int height) {
        super(x,y,width,height);//llama al constructor de gameObject 
    	
        destroyed = false;
        //Random r = new Random(x+y);
        
        cc = new Color(255, 0, 0,0);
       
  
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
