package game_pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public double x,y;
	public int width,height;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.width = 40;
		this.height = 5;
	}
	
	public void update() {
//		x+= (Game.ball.x - x - 6); // Lógica para seguir a bola
		x+= (Game.ball.x - x - 6) * 0.07; // Lógica para o inimigo errar
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
