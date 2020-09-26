package com.planoart.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.planoart.entities.Enemy;
import com.planoart.entities.Entity;
import com.planoart.entities.Player;
import com.planoart.entities.Shoot;
import com.planoart.graficos.Spritesheet;
import com.planoart.graficos.UserInterface;
//import com.planoart.world.Camera;
import com.planoart.world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean isRunning = false;
	
	public static JFrame frame;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	private final int SCALE = 3;
	private int LEVEL_ATUAL = 1, MAX_LEVEL = 2;
	
	private BufferedImage camada;
	
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<Shoot> shoots;
	public static Spritesheet spritesheet;
	
	public static World world;
	
	public static Player player;
	
	public static Random rand;
	
	public UserInterface userInterface;
	
	public static String estadoDoJogo = "NORMAL";
	private boolean mostrarMensagemGameOver = true, reiniciarJogo = false;
	private int mostrarMensagemGameOverFrames = 0, mostrarMensagemGameOverMaxFrames = 50;
	
	public Game() {
		
		rand = new Random();
		
		addKeyListener(this);
		addMouseListener(this);

		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		iniciarFrame();
		
		// Inicializando objetos:
		userInterface = new UserInterface();
		camada = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		shoots = new ArrayList<Shoot>();
		
		spritesheet = new Spritesheet("/Spritesheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(32, 0, 15, 15));
		entities.add(player);
		world = new World("/level1.png");
		
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
		
		if (estadoDoJogo == "NORMAL") {
			
			for (int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				entity.update();
			}
			
			for (int i = 0; i < shoots.size(); i++) {
				shoots.get(i).update();
			}
			
			if (enemies.size() == 0) {
				LEVEL_ATUAL++;
				
				if (LEVEL_ATUAL > MAX_LEVEL) {
					LEVEL_ATUAL = 1;
				}
				
				String newWorld = "level"+LEVEL_ATUAL+".png";
				World.reiniciarJogo(newWorld);			
			}			
			
		} else if (estadoDoJogo == "GAME_OVER") {
			
			mostrarMensagemGameOverFrames++;
			
			if (mostrarMensagemGameOverFrames == mostrarMensagemGameOverMaxFrames) {
				mostrarMensagemGameOverFrames = 0;

				mostrarMensagemGameOver = !mostrarMensagemGameOver;
			}
			
			if (reiniciarJogo) {
				
				LEVEL_ATUAL = 1;
				String newWorld = "level"+LEVEL_ATUAL+".png";
				estadoDoJogo = "NORMAL";
				World.reiniciarJogo(newWorld);	
				
			}
			
		}
	
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); // sequência de buffers para otimizar a renderização.
			return;
		}
		
		Graphics graficos = camada.getGraphics();
		graficos.setColor(new Color(0,0,0));
		graficos.fillRect(0, 0, WIDTH, HEIGHT);
		
		/************* Graficos do jogo **/
		
		world.render(graficos);
		
		for(int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.render(graficos);
		}
		
		for (int i = 0; i < shoots.size(); i++) {
			shoots.get(i).render(graficos);
		}		
		
		userInterface.render(graficos);
		
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
		
		graficos.dispose(); // Limpar dados da imagem que foram usado antes.
		
		graficos.setFont(new Font("Arial", Font.BOLD, 20));
		graficos.setColor(Color.white);
		graficos.drawString("SIT", 19, 19);
		
		graficos = bs.getDrawGraphics();
		graficos.drawImage(camada, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null); // renderizando imagem na tela.		
		
		if (estadoDoJogo == "GAME_OVER") {
			
			Graphics2D graficos2D = (Graphics2D) graficos;
			graficos2D.setColor(new Color(0,0,0,100));
			graficos2D.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			
			graficos.setFont(new Font("arial", Font.BOLD, 100));
			graficos.setColor(Color.white);
			
			// Procurar função para calcular exatamente o centro da tela
			graficos.drawString("DEU MOLE", 110, 290);
			
			graficos.setFont(new Font("arial", Font.BOLD, 20));
			graficos.setColor(Color.white);
			
			// Procurar função para calcular exatamente o centro da tela
			if (mostrarMensagemGameOver) graficos.drawString("Pressione enter", 300, 320);			
			
		}
		
		bs.show();

	}

	public void run() {
		
		long lastTime = System.nanoTime(); // Tempo atual do computador em nano segundos.
		double amountOfTicks = 60.0; 
		double ns = 1000000000 / amountOfTicks; // Dividindo 1 segundo pelo amountOfTicks.
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis(); // Tempo atual do computador em um formato menos preciso.
		
		requestFocus();
		
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
	
	// Evento do teclado:

	public void keyPressed(KeyEvent e) {
		
		if (estadoDoJogo == "NORMAL") {
		
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
			
			if (e.getKeyCode() == KeyEvent.VK_X) {
				player.shoot = true;
			}			
			
		}
		
		if (estadoDoJogo == "GAME_OVER" && e.getKeyCode() == KeyEvent.VK_ENTER) {
		
			reiniciarJogo = true;
			
		}
		
	}

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
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			reiniciarJogo = false;
			
		}		
		
	}
	
	// Eventos do mouse:

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if (estadoDoJogo == "NORMAL") {
			
			player.shootByMouse = true;
			
			// divido por 3 porcausa do scale
			player.posicaoMouseX = (e.getX() / 3); 
			player.posicaoMousey = (e.getY() / 3);			
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
