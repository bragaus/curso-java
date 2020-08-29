package oo;

public class Main extends TesteAbstract {

	public static void main(String[] args) {
		
		Player player = new Player();
		Inimigo inimigo = new Inimigo();
		
//		player.iniciarJogador(10, "real");
//		player.iniciarJogador(2, "cyberpunk");
//		inimigo.iniciarInimigo();
		
		new Main().instanceMain2();
	}
	
	public void chamarAbstract() {
		iniciarJogo();
	}
	
	public void instanceMain2() {
		new Main2().print1();
	}
	
	private class Main2 {
		public void print1() {
			System.out.println("classe privada");
		}
	}
	
}
