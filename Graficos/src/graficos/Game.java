package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

//	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean isRunning = false;
	
	public static JFrame frame;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 3;

	private BufferedImage camada;
	
	public Game() {
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		iniciarFrame();
		camada = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void iniciarFrame() {
		frame = new JFrame();
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
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public void update() {
		
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics grafico = camada.getGraphics();
		grafico.setColor(new Color(61,26,250));
		grafico.fillRect(0, 0, WIDTH, HEIGHT);
		grafico = bs.getDrawGraphics();
		grafico.drawImage(camada, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
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
	}
	
}
