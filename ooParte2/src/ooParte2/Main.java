package ooParte2;

public class Main {
	
//	private static int inteiro() {
//		return 10;
//	}
	
//	private static void Arrays(int[] n1, String[] nome) {
//		System.out.println(n1[0]);
//		System.out.println(nome[0]);
//	}
	
	private String nome;
	public static final int VIDA_MAXIMA = 100;
	
	// Método Construtor
	public Main() {
		nome = "Matheus";
		System.out.println("Classe criada! " + nome);
		System.out.println("Vida maxima " + VIDA_MAXIMA);
	}	
	
	public static void main(String[] args) {
		
		new Main();
		
//		Player player = new Player();
//		
//		player.iniciarPlayer();
//		
//		System.out.println(inteiro());
//		
//		int[] n1 = new int[10];
//		n1[0] = 10;
//		String[] nomes = new String[2];
//		nomes[0] = "João";
//		
//		Arrays(n1, nomes);
	}
	

}
