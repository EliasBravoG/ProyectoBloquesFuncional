package  com.mygdx.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall extends gameObject implements Movible{ //se agrega el extends gameObject y el implments movible 
	
	    private int xSpeed;
	    private int ySpeed;
	    private Color color = Color.WHITE;
	    private boolean estaQuieto;
	    
	    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
	        super(x,y,size,size); //usamos size como ancho y alto 
	    	
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        estaQuieto = iniciaQuieto;
	    }
	    
	    
	    public boolean estaQuieto() {
	    	return estaQuieto;
	    }
	    public void setEstaQuieto(boolean bb) {
	    	estaQuieto=bb;
	    }
	    public void setXY(int x, int y) {
	    	this.x = x;
	        this.y = y;
	    }
	    
	    //creamos getSize
	    public int getSize() {
	    	return width;
	    }
	    
	    public int getY() {return y;}
	    
	  
	    //agregagmos metodo abtracto heredado de gameobject 
	    @Override 
	    public void draw(ShapeRenderer shape) {
	        shape.setColor(color);
	        // usamos width heredado como si fuera size 
	        shape.circle(x, y, width);
	    }
	    
	    @Override 
	    public void update() {
	    	if(estaQuieto) {
	    		return;
	    	}
	    	x+=xSpeed;
	    	y+=ySpeed;
	    	
	    	//usamos width en lugar de size para alcular los rebotes
	    	if(x-width<0||x+width>Gdx.graphics.getWidth()) {
	    		xSpeed=-xSpeed;
	    	}
	    	if(y+width>Gdx.graphics.getHeight()) {
	    		ySpeed=-ySpeed;
	    	}
	    }

	    
	
	    
	    public void checkCollision(Paddle paddle) {
	    	if(collidesWith(paddle)) {
	    		color=Color.GREEN;
	    		ySpeed=-ySpeed;
	    	}else {
	    		color=Color.WHITE;
	    	}
	    }
	    
	    
	    
	    
	 
	    
	    private boolean collidesWith(Paddle pp) {
	    	//usamos pp-getX en vex de pp-x y usamos width como tamaño 
	    	boolean intersectaX=(pp.getX() + pp.getWidth() >= x - width) && (pp.getX() <= x + width);
	    	boolean intersectaY = (pp.getY() + pp.getHeight() >= y - width) && (pp.getY() <= y + width);
	    	return intersectaX && intersectaY;
	    	
	    }
	    
	
	    
	    public void checkCollision(Block block) {
	    	if(collidesWith(block)) {
	    		ySpeed=-ySpeed;
	    		//usamos el setter de block en lugar de bloc.destroyed como antes 
	    		block.setDestroyed(true);
	    	}
	    }
	 
	    
	    private boolean collidesWith(Block bb) {
	    	boolean intersectaX=(bb.getX() + bb.getWidth() >= x - width) && (bb.getX() <= x + width);
	    	boolean intersectaY = (bb.getY() + bb.getHeight() >= y - width) && (bb.getY() <= y + width);
	    	return intersectaX && intersectaY;
	    }
	    
	    
	    
	}
