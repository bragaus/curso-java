package com.planoart.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {

	private Tile[] tiles;
	public static int WIDTH, HEIGHT;
	
	public World(String path) {
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));	
			
			// conta para saber quantos pixels tem uma imagem: largura * altura
			int [] pixels = new int[map.getWidth() * map.getHeight()];
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();				
			
			tiles = new Tile[WIDTH * HEIGHT];
			
			map.getRGB(0, 0,WIDTH, HEIGHT, pixels, 0, WIDTH); // jogar os pixels da imagem dentro do array map.
			
			for (int xx = 0; xx < WIDTH; xx++) {
				
				for (int yy = 0; yy < HEIGHT; yy++) {
					
					int pixelAtual = pixels[xx + (yy * WIDTH)];
					
					switch (pixelAtual) {
					
					// Parede
					case 0xFFFFFFFF: tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL);
						break;
					
					// Parede, Player e default.
					case 0xFF000000: case 0xFF002EFF: default:
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
						break;
						
					}
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics graficos) {
		
		for (int xx = 0; xx < WIDTH; xx++) {
			
			for (int yy = 0; yy < HEIGHT; yy++) {
				
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(graficos);
				
			}
			
		}
		
	}
	
}
