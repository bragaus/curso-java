package com.planoart.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.planoart.main.Game;
import com.planoart.world.Camera;
import com.planoart.world.World;

public class Enemy extends Entity {
	
	private double speed = 0.5;
	private int maskx = 1, masky = 3, maskw = 10, maskh = 10;
	
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	
	private BufferedImage[] enemyAnimated;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		enemyAnimated = new BufferedImage[3];
		
		for (int i = 0; i < enemyAnimated.length; i++) {
			enemyAnimated[i] = Game.spritesheet.getSprite(112 + (i*16), 16, 15, 15);
		}
	}
	
	
	public void update() {
		
		//if (Game.rand.nextInt(100) < 50) { // movimento randomico do inimigo.
		
		if (isCollidingWithPlayer() == false) {
			
			// Lógica para inimigo seguir o player.
			if((int)x < Game.player.getX() 
					&& World.isFree((int)(x + speed), this.getY())
					&& !isColliding((int)(x + speed), this.getY())) {
				
				x += speed;
				
			} else if ((int)x > Game.player.getX() 
					&& World.isFree((int)(x - speed), this.getY())
					&& !isColliding((int)(x - speed), this.getY())) {
				
				x -= speed;
				
			}
			
			if((int)y < Game.player.getY() 
					&& World.isFree(this.getX(), (int)(y + speed))
					&& !isColliding(this.getX(), (int)(y + speed))) {
				
				y += speed;
				
			}
			
			else if((int) y > Game.player.getY() 
					&& World.isFree(this.getX(), (int)(y - speed))
					&& !isColliding(this.getX(), (int)(y - speed))) {
				
				y -= speed;
				
			}
			
	
			frames ++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index == maxIndex) index = 0;
			}
			
		} else {
			
			if (Game.rand.nextInt(100) < 10) { // 10%
				
				Player.life -= Game.rand.nextInt(3);
				Player.IsDamaged = true;
				
				System.out.println("Vida: " + Player.life);
				
				if (Player.life <= 0) {
					System.exit(1);
				}
				
			}

		}
		
	}
	
	public boolean isCollidingWithPlayer() {
		
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx, this.getY() + masky, 10, 15);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);
		
		return enemyCurrent.intersects(player);
	}
	
	public boolean isColliding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky,  maskw, maskh);
		
		for (int i = 0; i < Game.enemies.size(); i++) {
			
			Enemy e = Game.enemies.get(i);
			
			if (e == this) continue;
			
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw, maskh);
			
			if (enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
			
		}
		
		return false;
	}
	
	public void render(Graphics graficos) {
		
		int posicaoX = this.getX() - Camera.x;
		int posicaoY = this.getY() - Camera.y;		
		
		if (isCollidingWithPlayer()) {
			graficos.drawImage(enemyAnimated[0], posicaoX, posicaoY, null);
		} else {
			graficos.drawImage(enemyAnimated[index], posicaoX, posicaoY, null);
		}
		
		//super.render(graficos);
		//graficos.setColor(Color.blue);
		//graficos.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);

	}

}
