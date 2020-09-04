package game_pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 240;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Player player;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));		
		this.addKeyListener(this);
		player = new Player(100, HEIGHT-10);
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false); // Usuário não pode redimensionar a janela.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Quando clicar no fechar da janela, vai finalizar toda a aplicação.
	
		frame.add(game);
	
		frame.pack();
		
		frame.setLocationRelativeTo(null); // Tela sempre no meio.			
		frame.setVisible(true);
		
		new Thread(game).start();
		
	}
	
	public void update() {
		player.update();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy(); // basicamente onde é renderizado tudo do jogo.
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		bs.show(); // mostrar na tela
	}

	public void run() {
		while(true) {
			
			update();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
