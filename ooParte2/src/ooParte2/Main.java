package ooParte2;

public class Main {
	
	private static int inteiro() {
		return 10;
	}
	
	private static void Arrays(int[] n1, String[] nome) {
		System.out.println(n1[0]);
		System.out.println(nome[0]);
	}
	
	// Método Construtor
	public Main() {
		System.out.println("Classe criada!");
	}	
	
	public static void main(String[] args) {
		
		new Main();
		
		Player player = new Player();
		
		player.iniciarPlayer();
		
		System.out.println(inteiro());
		
		int[] n1 = new int[10];
		n1[0] = 10;
		String[] nomes = new String[2];
		nomes[0] = "João";
		
		Arrays(n1, nomes);
	}
	

}
