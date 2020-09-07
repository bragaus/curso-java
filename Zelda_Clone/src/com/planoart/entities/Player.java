package com.planoart.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity	{
	
	private double X;
	private double Y;	
	
	public boolean right, up, left, down;	
	public double speed = 1.4;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void update() {
		if (right) {
			setX(X+=speed);
		} else if (left) {
			setX(X-=speed);
		}
		
		if (up) {
			setY(Y-=speed);
		} else if (down) {
			setY(Y+=speed);
		}
	}
}
