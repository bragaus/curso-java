package com.planoart.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.planoart.main.Game;
import com.planoart.world.Camera;

public class Shoot extends Entity {

	private double direcaoX, direcaoY;
	private double speed = 4;
	
	private int tempoUtil = 30, tempoAtual = 0; // pensar qual formula para calcular a posição atual até a margem.
	
	public Shoot(int x, int y, int width, int height, BufferedImage sprite,  double direcaoX, double direcaoY) {
		super(x, y, width, height, sprite);
		
		this.direcaoX = direcaoX;
		this.direcaoY = direcaoY;
	}

	public void update() {
	
		x += direcaoX * speed;
		y += direcaoY * speed;
		
		// Lógica para detruir bala (otimização)
		tempoAtual++;
		if (tempoAtual == tempoUtil) {
			Game.shoots.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics graficos) {

		graficos.setColor(Color.YELLOW);
		graficos.fillOval(this.getX() - Camera.x, this.getY()  - Camera.y, width, height);
		
	}
	
}
