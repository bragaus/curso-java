package com.planoart.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.planoart.graficos.Spritesheet;
import com.planoart.main.Game;
import com.planoart.world.Camera;
import com.planoart.world.World;

public class Player extends Entity	{
	
	public boolean right, up, left, down;	
	public double speed = 1.4;

	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private boolean moved;
	
	// Player
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] backPlayer;
	private BufferedImage[] frontPlayer;
	
	// Player com dano
	private BufferedImage playerDamage; 
	
	private BufferedImage[] weapon;
	
	public static boolean IsDamaged = false;
	private boolean hasGun = false;
	
	public static int ammo = 0;
	
	private int damageFrames = 0;
	
	public boolean shoot = false, shootByMouse = false;
	
	public double life = 100, maxLife = 100;
	public int posicaoMouseX, posicaoMousey;
	
	private boolean isBackPlayer; // Auxiliar para validar se o player parou de costas.

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		backPlayer = new BufferedImage[1];
		frontPlayer = new BufferedImage[1];
		weapon = new BufferedImage[2];
		
		playerDamage = Game.spritesheet.getSprite(0, 30, 14, 15);
		
		weapon[0] = Game.spritesheet.getSprite(112, 0, 14, 15);
		weapon[1] = Game.spritesheet.getSprite(128, 0, 14, 15);

		for(int i = 0; i < backPlayer.length; i++) {
			
			frontPlayer[i] = Game.spritesheet.getSprite(17, 15, 15, 15);
			
		}		
		
		for(int i = 0; i < backPlayer.length; i++) {
			
			backPlayer[i] = Game.spritesheet.getSprite(0, 15, 15, 15);
			
		}		

		for(int i = 0; i < rightPlayer.length; i++) {
			
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 15, 15);
			
		}
		
		for(int i = 0; i < leftPlayer.length; i++) {
			
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 15, 15, 15);
			
		}

		
	}
	
	public void update() {
		
		moved = false;
		
		// World.isFree((int)(x + speed), this.getY()) verificando colisão
		
		if (right && World.isFree((int)(x + speed), this.getY()) ) {
			
			moved = true;
			isBackPlayer = false;
			setX(x+=speed);
			
		} else if (left && World.isFree((int)(x - speed), this.getY()) ) {
			
			moved = true;
			isBackPlayer = false;
			setX(x-=speed);
			
		}
		
		if (up && World.isFree(this.getX(), (int)(y - speed)) ) {
			
			moved = true;
			isBackPlayer = true;
			setY(y-=speed);
			
		} else if (down && World.isFree(this.getX(), (int)(y + speed)) ) {
			
			moved = true;
			isBackPlayer = false;
			setY(y+=speed);
			
		}
		
		// Logica da animação
		if (moved) {
			frames ++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index == maxIndex) index = 0;
			}
		}
		
		checkCollisionLifePack();
		checkCollisionAmmo();
		checkCollisionGun();
		
		// animação para piscar quando der dano.
		if (IsDamaged) {
			
			this.damageFrames++;
			if (this.damageFrames == 8) {
				this.damageFrames = 0;
				IsDamaged = false;
			}
			
		}
		
		// Sistema para atirar com o teclado (X)
		if (shoot) {
			
			shoot = false;
			
			if (hasGun && ammo > 0) {
				ammo--;
				
				int direcaoX = 0;
				
				if (right) {
					direcaoX = 1;
				} else {
					direcaoX = -1;
				}
				
				Shoot shoot = new Shoot(this.getX(), this.getY() + 7 , 3, 3, null, direcaoX, 0);
				Game.shoots.add(shoot);				
			}

		}
		
		if (shootByMouse) {
			
			shootByMouse = false;
			
//			if (hasGun && ammo > 0) {
//				ammo--;
				
				int posicaoX = 0, posicaoY = 8;
				double angulo = 0;
				
				if (right) {
					
					posicaoX = 18;				
					
					// Calcular o angulo do tiro (usado em jogos fps)
					angulo = Math.atan2(
						posicaoMousey - (this.getY() + posicaoY - Camera.y), 
						posicaoMouseX - (this.getX() + posicaoX - Camera.x)
					);					
					
				} else {
					
					posicaoX = -8;				
					
					// Calcular o angulo do tiro (usado em jogos fps)
					angulo = Math.atan2(
						posicaoMousey - (this.getY() + posicaoY - Camera.y), 
						posicaoMouseX - (this.getX() + posicaoX - Camera.x)
					);
					
				}
				
				double direcaoX = Math.cos(angulo);
				double direcaoY = Math.sin(angulo);				
				
				Shoot shoot = new Shoot(this.getX() + posicaoX, this.getY() + posicaoY , 3, 3, null, direcaoX, direcaoY);
				Game.shoots.add(shoot);
				
//			}

		}		
		
		// Reiniciar o jogo quando acabar a vida.
		if (life <= 0) {
			
			Game.entities = new ArrayList<Entity>();
			Game.enemies = new ArrayList<Enemy>();
			
			Game.spritesheet = new Spritesheet("/Spritesheet.png");
			Game.player = new Player(0,0,16,16,Game.spritesheet.getSprite(32, 0, 15, 15));
			Game.entities.add(Game.player);
			Game.world = new World("/map.png");
			
			return;
			
		}
		
		// travar a camera no limite do mapa.
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT*16 - Game.HEIGHT);
		
	}
	
	// Colisão na munição
	public void checkCollisionGun() {
		
		for (int i = 0; i < Game.entities.size(); i++) {
			
			Entity objetoAtual = Game.entities.get(i);
			
			if (objetoAtual instanceof Weapon) {
				
				if (Entity.isColliding(this, objetoAtual)) {
					hasGun = true;
					Game.entities.remove(i);
					return;
				}
			}
		}
	}	
	
	// Colisão na munição
	public void checkCollisionAmmo() {
		
		for (int i = 0; i < Game.entities.size(); i++) {
			
			Entity objetoAtual = Game.entities.get(i);
			
			if (objetoAtual instanceof Bullet) {
				
				if (Entity.isColliding(this, objetoAtual)) {
					ammo++;
					Game.entities.remove(i);
					// System.out.println("Munição atual: " + ammo);
					return;
				}
			}
		}
	}
	
	// Colisão no lifepack
	public void checkCollisionLifePack() {
		
		for (int i = 0; i < Game.entities.size(); i++) {
			
			Entity objetoAtual = Game.entities.get(i);
			
			if (objetoAtual instanceof Lifepack) {
				
				if (Entity.isColliding(this, objetoAtual)) {
					life += 8;
					if (life > 100) life = 100;
					Game.entities.remove(i);
					return;
				}
			}	
		}
	}
	
	public void render(Graphics graficos) {
		
		int posicaoX = this.getX() - Camera.x;
		int posicaoY = this.getY() - Camera.y;
		
		if (!IsDamaged) {
			
			if (right) {
				graficos.drawImage(rightPlayer[index], posicaoX, posicaoY, null);
				
				if (hasGun) {
					graficos.drawImage(Entity.GUN_RIGHT, posicaoX, posicaoY + 2, null);
				}
				
			} else  if (left) {
				graficos.drawImage(leftPlayer[index], posicaoX, posicaoY, null);
				
				if (hasGun) {
					graficos.drawImage(Entity.GUN_LEFT, posicaoX, posicaoY + 2, null);
				}				
				
			} else {
				graficos.drawImage(frontPlayer[0], posicaoX, posicaoY, null);
			}

			if (up) {
				graficos.drawImage(backPlayer[0], posicaoX, posicaoY, null);
			} else if (isBackPlayer) {
				graficos.drawImage(backPlayer[0], posicaoX, posicaoY, null);
			}
			
			if (down) {
				graficos.drawImage(frontPlayer[0], posicaoX, posicaoY, null);
			}			
			
		} else {
			
			graficos.drawImage(playerDamage, posicaoX, posicaoY, null);
			
		}
		
	}
}
