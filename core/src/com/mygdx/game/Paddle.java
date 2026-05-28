package  com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle extends gameObject implements Movible{ //se agrega extends gameObject y implmentes movilble 
    //private int x = 20;
    //private int y = 20;
    //private int width = 100;
    //private int height = 10;
    
    public Paddle(int x, int y, int ancho, int alto) {
    	super(x,y,ancho,alto);
    	//this.x = x;
    	//this.y= y;
    	//width = ancho;
    	//height = alto;
    }
    @Override 
    
    public void update() {
    	int x2=x;
    	
    	//detectar las teclas presionaas
    	if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		x2=x-15;
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
    		x2=x+15;
    	}
    	
    	//validar queno se salga del borde de la ventana
    	if(x2>0&&x2+width<Gdx.graphics.getWidth()) {
    		x=x2;
    	}
    }
     
    public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	
	@Override
	public void draw(ShapeRenderer shape){
        shape.setColor(Color.BLUE);
        //int x2 = x; //= Gdx.input.getX();
        //if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 =x-15;
        //if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2=x+15; 
       // y = Gdx.graphics.getHeight() - Gdx.input.getY(); 
        //if (x2 > 0 && x2+width < Gdx.graphics.getWidth()) {
        //    x = x2;
       // }
        shape.rect(x, y, width, height);
    }
    
    
}
