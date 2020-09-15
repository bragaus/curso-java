package com.planoart.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.planoart.world.Camera;

public class Shoot extends Entity {

	private int direcaoX, direcaoY;
	private double speed;
	
	public Shoot(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void update() {
	
		x += direcaoX * speed;
		y += direcaoY * speed;
		
	}
	
	public void render(Graphics graficos) {
		
		graficos.setColor(Color.YELLOW);
		graficos.fillOval(this.getX() - Camera.x, this.getY()  - Camera.y, 3, 3);
		
		
	}
	
}
