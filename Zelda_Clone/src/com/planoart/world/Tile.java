package com.planoart.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.planoart.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 15, 15);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 15, 15);
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics grafico) {
		grafico.drawImage(sprite, x, y, null);
	}
	
}
