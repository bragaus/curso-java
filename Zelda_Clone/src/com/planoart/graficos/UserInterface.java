package com.planoart.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.planoart.entities.Player;
import com.planoart.main.Game;

public class UserInterface {

	public void render(Graphics graficos) {
		
		// Vida
		graficos.setColor(Color.red);
		graficos.fillRect(2, 3, 50, 10);		
		
		graficos.setColor(Color.green);
		graficos.fillRect(2, 3, (int)((Game.player.life/Game.player.maxLife)*50), 10);
		
		graficos.setColor(Color.white);
		graficos.setFont(new Font("arial", Font.BOLD, 9));
		graficos.drawString((int)Game.player.life+"/"+(int)Game.player.maxLife, 3, 11);
		
		// Munição
		graficos.setColor(Color.white);
		graficos.setFont(new Font("arial", Font.BOLD, 9));
		graficos.drawString("AMMO: " + Player.ammo, 3, 21);		
	}
	
}
