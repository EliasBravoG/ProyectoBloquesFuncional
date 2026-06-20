package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

//import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block extends gameObject { 
	private Color cc;
	private boolean destroyed;
	
    
    
    public Block(int x, int y, int width, int height) {
        super(x,y,width,height);
    	
        destroyed = false;
        //Random r = new Random(x+y);
        
        cc = new Color(255, 0, 0,0);
       
  
    }
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
    
    public boolean isDestroyed() {
    	return destroyed;
    }
    
    public void setDestroyed(boolean destroyed) {
    	this.destroyed=destroyed;
    }
}
