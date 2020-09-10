package com.planoart.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.planoart.entities.Entity;
import com.planoart.entities.Player;
import com.planoart.graficos.Spritesheet;
import com.planoart.world.World;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean isRunning = false;
	
	public static JFrame frame;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	private final int SCALE = 3;
	private BufferedImage camada;
	
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	
	public static World world;
	
	public static Player player;
	
	public Game() {
		
		addKeyListener(this);

		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		iniciarFrame();
		
		// Inicializando objetos:
		camada = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		entities = new ArrayList<Entity>();
		
		spritesheet = new Spritesheet("/Spritesheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(32, 0, 15, 15));
		entities.add(player);
		world = new World("/map.png");
		
	}
	
	public void iniciarFrame() {
		frame = new JFrame("SIT");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public void update() {
		
		for(int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.update();
		}
	
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); // sequência de buffers para otimizar a renderização.
			return;
		}
		
		Graphics grafico = camada.getGraphics();
		grafico.setColor(new Color(0,0,0));
		grafico.fillRect(0, 0, WIDTH, HEIGHT);
		
		/************* Graficos do jogo **/
		
		world.render(grafico);
		
		for(int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.render(grafico);
		}		
		
		// filtrando um método específico, isso se chama Casting no Java. 
		// Graphics2D grafico2D = (Graphics2D) grafico;	
		
		// +8 = metade do tamanho total do sprite, isso foi feito para posicionar o eixo de rotação no meio do sprite.
		// grafico2D.rotate(Math.toRadians(90), 90+8, 90+8); 

		
		// No rotate do Java, é necessário voltar o valor que foi setado anteriormente 
		// para voltar ao normal e depois ser usado denovo.
		// grafico2D.rotate(Math.toRadians(-90), 90+8, 90+8); 		
		// grafico.setColor(new Color(0,0,0,100));
		// grafico.fillRect(0, 0, WIDTH, HEIGHT);			
		
		/*************/
		
		grafico.dispose(); // Limpar dados da imagem que foram usado antes.
		
		grafico.setFont(new Font("Arial", Font.BOLD, 20));
		grafico.setColor(Color.white);
		grafico.drawString("SIT", 19, 19);
		
		grafico = bs.getDrawGraphics();
		grafico.drawImage(camada, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null); // renderizando imagem na tela.
		bs.show();

	}

	public void run() {
		
		long lastTime = System.nanoTime(); // Tempo atual do computador em nano segundos.
		double amountOfTicks = 60.0; 
		double ns = 1000000000 / amountOfTicks; // Dividindo 1 segundo pelo amountOfTicks.
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis(); // Tempo atual do computador em um formato menos preciso.
		
		while(isRunning) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) { // Se passou 1 segundo:
				update();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// Seta direita ou D pressionado.
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
		// Seta baixo ou S pressionado.
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
				
	}

	@Override
	public void keyReleased(KeyEvent e) {

		// Seta direita ou D pressionado.
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		// Seta baixo ou S pressionado.
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
