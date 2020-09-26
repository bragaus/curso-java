package com.planoart.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.planoart.entities.Bullet;
import com.planoart.entities.Enemy;
import com.planoart.entities.Entity;
import com.planoart.entities.Lifepack;
import com.planoart.entities.Player;
import com.planoart.entities.Weapon;
import com.planoart.graficos.Spritesheet;
import com.planoart.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
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
					
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					
					switch (pixelAtual) {
					
					// Parede
					case 0xFFFFFFFF: tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
						break;
						
					// Parede
					case 0xFF002EFF: Game.player.setX(xx*16); Game.player.setY(yy*16);						
						break;
					
					// Vida
					case 0xFFFF0094: 
						Lifepack pack = new Lifepack(xx*16,yy*16,16,16,Entity.LIFEPACK_EN);
						// pack.setMask(1, 1, 8, 1);
						Game.entities.add(pack);
						break;
					
					// Munição
					case 0xFFFFE500: Game.entities.add(new Bullet(xx*16,yy*16,16,16,Entity.BULLET_EN));
						break;
						
					// Arma
					case 0xFFFF8E00: Game.entities.add(new Weapon(xx*16,yy*16,16,16,Entity.WEAPON_EN));
						break;
						
					// Inimigo
					case 0xFFFF0000: 
						Enemy en = new Enemy(xx*16,yy*16,16,16,Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
						break;
						
					// Chão
					case 0xFF000000:  default:
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
						break;
						
					}
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void reiniciarJogo(String level) {
		
		Game.entities = new ArrayList<Entity>();
		Game.enemies = new ArrayList<Enemy>();
		
		Game.spritesheet = new Spritesheet("/Spritesheet.png");
		Game.player = new Player(0,0,16,16,Game.spritesheet.getSprite(32, 0, 15, 15));
		Game.entities.add(Game.player);
		Game.world = new World("/"+level);
		
		return;		
		
	}
	
	// Sistema de colisão
	public static boolean isFree(int xnext, int ynext) {
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !(
			tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile ||
			tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile ||
			tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile ||
			tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile
		);
		
	}
	
	public void render(Graphics graficos) {
		
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for (int xx = xstart; xx <= xfinal; xx++) {
			
			for (int yy = ystart; yy <= yfinal; yy++) {
				
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) continue;

				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(graficos);

			}
			
		}
		
	}
	
}
