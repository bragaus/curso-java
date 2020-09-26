package com.planoart.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public String[] opcoes = {"novo jogo", "carregar jogo", "sair"};
	
	public int opcaoAtual = 0;
	public int opcaoMaxima = opcoes.length - 1;
	
	public boolean up, down;

	public void update() {
		
		if (up) {
			up = false;
			if (opcaoAtual <= 0) {
				opcaoAtual = opcaoMaxima;
			} else {
				opcaoAtual--;
			}
			
		} 
		
		if (down) {
			down = false;
			opcaoAtual++;
			if (opcaoAtual > opcaoMaxima) {
				opcaoAtual = 0;
			}
		}
		
	}
	
	public void render(Graphics graficos) {
		
		graficos.setColor(Color.black);
		graficos.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		graficos.setFont(new Font("arial", Font.BOLD, 50));
		graficos.setColor(Color.white);
		graficos.drawString("MENU", 10, 50);
		
		
		graficos.setFont(new Font("arial", Font.BOLD, 20));
		graficos.setColor(Color.white);
		
		graficos.drawString("novo jogo", 300, 200);

		graficos.drawString("carregar jogo", 300, 220);

		graficos.drawString("sair", 300, 240);
		
		switch (opcoes[opcaoAtual]) {
		
		case "novo jogo": graficos.drawString(">", 280, 200);
			break;
		
		case "carregar jogo": graficos.drawString(">", 280, 220);
			break;
			
		case "sair": graficos.drawString(">", 280, 240);
			break;
			
		default: graficos.drawString(">", 280, 200);
		
		}
		
		
	}
	
}