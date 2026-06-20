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
			
			camera.update();
			
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			
			ManejoDePuntaje pv=ManejoDePuntaje.getInstance();
			
			
			font.draw(batch,"Puntos: "+pv.getPuntaje(),10,25);
			
			
			font.draw(batch, "Nivel : " + nivel, Gdx.graphics.getWidth()/2 - 40, 25);

		
			font.draw(batch, "Vidas : " + pv.getVidas(), Gdx.graphics.getWidth()-120, 25);
			batch.end();
		}	
		
		public PingBall getBall() {
			return ball;
		}
		
		public void reiniciarBola() {
			
			ball = new PingBallBuilder().setPosicion(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11).setSize(10).setVelocidad(5, 7).setInicioQuieto(true).build();
		}
		 public void subirNivel() {
			 nivel++;
			 ManejoDePuntaje pv = ManejoDePuntaje.getInstance();

			 if (nivel >= 3){
				 pv.setEstrategia(new EfectoMasPuntos());
			 }
			 crearBloques(2+nivel);
			 ball.setXSpeed(ball.getXSpeed() + 1);
			 ball.setYSpeed(ball.getYSpeed() + 1);
		 }

		 public int getNivel() {
			 return nivel;
		 }

		@Override
		public void render () {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 		
	        shape.begin(ShapeRenderer.ShapeType.Filled);
	        
	        pad.update();
	        gameObject objetoPaleta = pad;
			objetoPaleta.draw(shape);
	        
	        ManejoDePuntaje pv= ManejoDePuntaje.getInstance();

	        
	        if (ball.estaQuieto()) {
	        	ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
	        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
					ball.setEstaQuieto(false);
				}
			}

			else {
				Movible pelotaMovil = ball;
				pelotaMovil.update();
			}
	        
	        if (ball.getY()<0) {
	        	SecuenciaEvento perderVida =new SecuenciaPerderVida();
	        	perderVida.ejecutarSecuencia(this,pv);
	        	
	        }
	        
	        if (pv.getVidas()<=0) {
				System.out.println("GAME OVER");
	        	pv.reset();
	        	nivel = 1;
	        	crearBloques(2+nivel);
				reiniciarBola();
	        	        	
	        }
	        
	        if (blocks.isEmpty()) {
				System.out.println("Nivel completado!");
	        	SecuenciaEvento pasarNivel=new PasarNivel();
	        	pasarNivel.ejecutarSecuencia(this,pv);
	        	
	        }    	
	        
	        for (Block b : blocks) {        	
	            b.draw(shape);
	            ball.checkCollision(b);
	        }
	        
	        
	        for (int i = 0; i < blocks.size(); i++) {
	            Block b = blocks.get(i);
	            if (b.isDestroyed()) { 
	            	pv.sumarPuntos(1); 
	                blocks.remove(b);
	                i--; 
	            }
	        }
	        
	        ball.checkCollision(pad);
			gameObject objetoPelota = ball;
			objetoPelota.draw(shape);
	        
	        shape.end();
	        dibujaTextos();
		}
		
		@Override
		public void dispose () {

		}
	}
