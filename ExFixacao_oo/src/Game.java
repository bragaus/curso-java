
public class Game {
	
	private Player player;
	private Inimigo inimigo;
	
	public Game() {
//		player = new Player();
		
		if(player == null) {
			System.out.println("O player não foi inicializado");
			player = new Tipo1();
		}
		
		if(player instanceof Player) {
			System.out.println("O player foi incializado com sucesso e faz referências a claase player");
		} else if (player instanceof Tipo1) {
			System.out.println("O player faz parte do Tipo1");
		}
		
		inimigo = new Inimigo();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Inimigo getInimigo() {
		return inimigo;
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		Player player = game.getPlayer();
		player.atacarInimigo(game.getInimigo());
		System.out.println(game.getInimigo().life);
		
	}

}  
