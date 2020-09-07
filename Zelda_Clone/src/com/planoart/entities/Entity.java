package com.planoart.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	
	private double x;
	private	double y;
	private int width;
	private int height;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
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
	
	public void render(Graphics graficos) {
		graficos.drawImage(this.getSpritesheet(), this.getX(), this.getY(), null);
	}
	

}
