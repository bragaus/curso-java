package com.planoart.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.planoart.entities.Player;

public class UserInterface {

	public void render(Graphics graficos) {
		graficos.setColor(Color.red);
		graficos.fillRect(2, 3, 50, 10);		
		
		graficos.setColor(Color.green);
		graficos.fillRect(2, 3, (int)((Player.life/Player.maxLife)*50), 10);
		
		graficos.setColor(Color.white);
		graficos.setFont(new Font("arial", Font.BOLD, 9));
		graficos.drawString((int)Player.life+"/"+(int)Player.maxLife, 3, 11);
	}
	
}
