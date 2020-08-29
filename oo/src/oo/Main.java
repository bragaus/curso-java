package oo;

public class Main {

	public static void main(String[] args) {
		
		Player player = new Player();
		Inimigo inimigo = new Inimigo();
		
		player.iniciarJogador(10, "real");
		player.iniciarJogador(2, "cyberpunk");
		inimigo.iniciarInimigo();
		
	}
	
}
