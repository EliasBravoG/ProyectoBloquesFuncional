package com.mygdx.game;

import java.util.ArrayList;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class BlockBreakerGame extends ApplicationAdapter {
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private ShapeRenderer shape;
	private PingBall ball;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int nivel;
    
		@Override
		public void create () {	
			camera = new OrthographicCamera();
		    camera.setToOrtho(false, 800, 480);
		    batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    nivel = 1;
		    crearBloques(2+nivel);
			
		    shape = new ShapeRenderer();
		    ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);
		    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
		    //vidas = 3;
		    //puntaje = 0;    
		}
		public void crearBloques(int filas) {
			blocks.clear();
			int blockWidth = 70;
		    int blockHeight = 26;
		    int y = Gdx.graphics.getHeight();
		    for (int cont = 0; cont<filas; cont++ ) {
		    	y -= blockHeight+10;
		    	for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
		            blocks.add(new Block(x, y, blockWidth, blockHeight));
		        }
		    }
		}
		public void dibujaTextos() {
			//actualizar matrices de la cámara
			camera.update();
			//actualizar 
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			
			ManejoDePuntaje pv=ManejoDePuntaje.getInstance();
			
			font.draw(batch,"Puntos: "+pv.getPuntaje(),10,25);
			
			
			font.draw(batch, "Vidas : " + pv.getVidas(), Gdx.graphics.getWidth()-20, 25);
			batch.end();
		}	
		//metodos para el method temple 
		public PingBall getBall() {
			return ball;
		}
		
		public void reiniciarBola() {
			//ball=new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
			ball = new PingBallBuilder().setPosicion(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11).setSize(10).setVelocidad(5, 7).setInicioQuieto(true).build();
		}
		 public void subirNivel() {
			 nivel++;
			 crearBloques(2+nivel);
		 }
		@Override
		public void render () {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 		
	        shape.begin(ShapeRenderer.ShapeType.Filled);
	        
	        pad.update();//llamamos a update de la paleta para que responda el teclado 
	        pad.draw(shape);
	        
	        ManejoDePuntaje pv= ManejoDePuntaje.getInstance();//llamada del singleton
	        
	        // monitorear inicio del juego
	        if (ball.estaQuieto()) {
	        	ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
	        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
	        }else ball.update();
	        //verificar si se fue la bola x abajo
	        if (ball.getY()<0) {
	        	SecuenciaEvento perderVida =new SecuenciaPerderVida();
	        	perderVida.ejecutarSecuencia(this,pv);
	        	
	        }
	        // verificar game over
	        if (pv.getVidas()<=0) {
	        	pv.reset();
	        	nivel = 1;
	        	crearBloques(2+nivel);
	        	        	
	        }
	        // verificar si el nivel se terminó
	        if (blocks.size()==0) {
	        	SecuenciaEvento pasarNivel=new PasarNivel();
	        	pasarNivel.ejecutarSecuencia(this,pv);
	        	
	        }    	
	        //dibujar bloques
	        for (Block b : blocks) {        	
	            b.draw(shape);
	            ball.checkCollision(b);
	        }
	        
	        //neuvo de strategy 
	        
	        //si se presiona D se dobla el putnaje 
	        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
	        	pv.setEstrategia(new EfectoMasPuntos());
	        }
	        //si se presiona N se vielve al puntaje normal 
	        if(Gdx.input.isKeyPressed(Input.Keys.N)) {
	        	pv.setEstrategia(new PuntajeNormal());
	        }
	        //fin codigo strtegy 
	        
	        
	        // actualizar estado de los bloques 
	        for (int i = 0; i < blocks.size(); i++) {
	            Block b = blocks.get(i);
	            if (b.isDestroyed()) { //se cambia b.destroyed pro b.isDestroyed
	            	pv.sumarPuntos(1); 
	                blocks.remove(b);
	                i--; //para no saltarse 1 tras eliminar del arraylist
	            }
	        }
	        
	        ball.checkCollision(pad);
	        ball.draw(shape);
	        
	        shape.end();
	        dibujaTextos();
		}
		
		@Override
		public void dispose () {

		}
	}
