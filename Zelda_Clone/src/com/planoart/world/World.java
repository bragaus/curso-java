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
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			// conta para saber quantos pixels tem uma imagem: largura * altura
			int [] pixels = new int[WIDTH * HEIGHT];
			
			tiles = new Tile[WIDTH * HEIGHT];
			
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH); // jogar os pixels da imagem dentro do array map.
			
			// Percorrendo o mapa
			for (int x = 0; x < WIDTH; x++) {
				
				for (int y = 0; y < HEIGHT; y++) {
					
					int pixelAtual = pixels[x + (y * WIDTH)];
					
					switch(pixelAtual) {
					
					case 0xFF000000: // chão
						tiles[x + (y * WIDTH)] = new FloorTile(x*16, y*16, Tile.TILE_WALL);
						break;
						
					case 0xFFFFFFFF:  // parede
						tiles[x + (y * WIDTH)] = new FloorTile(x*16, y*16, Tile.TILE_FLOOR);
						break;
						
//					case 0xFFFF0000:
//						System.out.println("Vermelho");
//						break;
//						
//					case 0xFFFFE500:
//						System.out.println("Amarelo");
//						break;
						
//					case 0xFF002EFF:
//						System.out.println("player");
//						break;
						
//					default:
//						// chão
					
					}
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics grafico) {
		
		for (int x = 0; x < WIDTH; x++) {
			
			for (int y = 0; y < HEIGHT; y++) {
				
				Tile tile = tiles[x + (y*WIDTH)];
				tile.render(grafico);
				
			}
			
		}
		
	}
	
}
