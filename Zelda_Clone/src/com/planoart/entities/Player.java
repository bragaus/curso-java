package com.planoart.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.planoart.main.Game;

public class Player extends Entity	{
	
	private double X;
	private double Y;
	
	public boolean right, up, left, down;	
	public double speed = 1.4;

	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private boolean moved;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] backPlayer;
	private BufferedImage[] frontPlayer;
	
	private boolean isBackPlayer; // Auxiliar para validar se o player parou de costas.

	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		backPlayer = new BufferedImage[1];
		frontPlayer = new BufferedImage[1];

		for(int i = 0; i < backPlayer.length; i++) {
			
			frontPlayer[i] = Game.spritesheet.getSprite(17, 15, 15, 15);
			
		}		
		
		for(int i = 0; i < backPlayer.length; i++) {
			
			backPlayer[i] = Game.spritesheet.getSprite(0, 15, 15, 15);
			
		}		

		for(int i = 0; i < rightPlayer.length; i++) {
			
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 15, 15);
			
		}
		
		for(int i = 0; i < leftPlayer.length; i++) {
			
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 15, 15, 15);
			
		}

		
	}
	
	public void update() {
		
		moved = false;
		
		if (right) {
			
			moved = true;
			isBackPlayer = false;
			setX(X+=speed);
			
		} else if (left) {
			
			moved = true;
			isBackPlayer = false;
			setX(X-=speed);
			
		}
		
		if (up) {
			
			moved = true;
			isBackPlayer = true;
			setY(Y-=speed);
			
		} else if (down) {
			
			moved = true;
			isBackPlayer = false;
			setY(Y+=speed);
			
		}
		
		// Logica da animação
		if (moved) {
			frames ++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index == maxIndex) index = 0;
			}
		}
	}
	
	public void render(Graphics graficos) {
		
		if (right) {
			graficos.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
		} else  if (left) {
			graficos.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
		} else {
			graficos.drawImage(frontPlayer[0], this.getX(), this.getY(), null);
		}

		if (up) {
			graficos.drawImage(backPlayer[0], this.getX(), this.getY(), null);
		} else if (isBackPlayer) {
			graficos.drawImage(backPlayer[0], this.getX(), this.getY(), null);
		}
		
		if (down) {
			graficos.drawImage(frontPlayer[0], this.getX(), this.getY(), null);
		}
		
	}
}
