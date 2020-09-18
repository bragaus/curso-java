package com.planoart.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.planoart.main.Game;
import com.planoart.world.Camera;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(6*16, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(7*16, 0, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(6*16, 16, 16, 16);
	public static BufferedImage GUN_RIGHT = Game.spritesheet.getSprite(112, 0, 15, 15);
	public static BufferedImage GUN_LEFT = Game.spritesheet.getSprite(128, 0, 15, 15);
	
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(7*16, 16, 15, 15);
	public static BufferedImage ENEMY_FEEDBACK = Game.spritesheet.getSprite(143, 31, 15, 15);
		
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	private int maskx, masky, minwidth, minheight;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.minwidth = width;
		this.minheight = height;
	}
	
	public void setMask(int maskx, int masky, int minwidth, int minheight) {
		
		this.maskx = maskx;
		this.masky = masky;
		this.minwidth = minwidth;
		this.minheight = minheight;
		
	}
	
	public void setX(double newX) {
		this.x = newX;
	}
	
	public void setY(double newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage getSpritesheet() {
		return sprite;
	}
	
	public void update() {
		
	}
	
	public static boolean isColliding(Entity e1, Entity e2) {
		
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.minwidth, e1.minheight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.minwidth, e2.minheight);
		
		return e1Mask.intersects(e2Mask);
		
	}
	
	public void render(Graphics graficos) {
		graficos.drawImage(this.getSpritesheet(), this.getX() - Camera.x, this.getY() - Camera.y, null);
		//graficos.setColor(Color.red);
		//graficos.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, minwidth, height);
	}
	

}
