package com.planoart.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.planoart.main.Game;
import com.planoart.world.Camera;
import com.planoart.world.World;

public class Player extends Entity	{
	
//	private double X;
//	private double Y;
	
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
		
		// World.isFree((int)(x + speed), this.getY()) verificando colis�o
		
		if (right && World.isFree((int)(x + speed), this.getY()) ) {
			
			moved = true;
			isBackPlayer = false;
			setX(x+=speed);
			
		} else if (left && World.isFree((int)(x - speed), this.getY()) ) {
			
			moved = true;
			isBackPlayer = false;
			setX(x-=speed);
			
		}
		
		if (up && World.isFree(this.getX(), (int)(y - speed)) ) {
			
			moved = true;
			isBackPlayer = true;
			setY(y-=speed);
			
		} else if (down && World.isFree(this.getX(), (int)(y + speed)) ) {
			
			moved = true;
			isBackPlayer = false;
			setY(y+=speed);
			
		}
		
		// Logica da anima��o
		if (moved) {
			frames ++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index == maxIndex) index = 0;
			}
		}
		
		// travar a camera no limite do mapa.
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT*16 - Game.HEIGHT);
		
	}
	
	public void render(Graphics graficos) {
		
		int posicaoX = this.getX() - Camera.x;
		int posicaoY = this.getY() - Camera.y;
		
		if (right) {
			graficos.drawImage(rightPlayer[index], posicaoX, posicaoY, null);
		} else  if (left) {
			graficos.drawImage(leftPlayer[index], posicaoX, posicaoY, null);
		} else {
			graficos.drawImage(frontPlayer[0], posicaoX, posicaoY, null);
		}

		if (up) {
			graficos.drawImage(backPlayer[0], posicaoX, posicaoY, null);
		} else if (isBackPlayer) {
			graficos.drawImage(backPlayer[0], posicaoX, posicaoY, null);
		}
		
		if (down) {
			graficos.drawImage(frontPlayer[0], posicaoX, posicaoY, null);
		}
		
	}
}
