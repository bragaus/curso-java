
public class Game {
	
	private Player player;
	private Inimigo inimigo;
	
	public Game() {
		player = new Player();
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
