package programa_leitura;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Por favor digite o seu nome:");
		String nome = in.nextLine();
		
		System.out.println("Por favor digite o sua idade:");
		String idade = in.nextLine();
		
		System.out.println("Por favor digite o livro que você está lendo:");
		String lendo = in.nextLine();
		
		if(lendo == "Neuromancer") {
			System.out.println("Show! também estou lendo esse livro");
		} else {
			System.out.println("VC PRECISAR LER NEUROMANCER AAAAAA");
		}
		
		System.out.println("Por favor digite o seu livro favorito:");
		String favorito = in.nextLine();
		
		System.out.println("-----------------------------------");
		System.out.println("O resultado é:");
		System.out.println("Nome: " + nome);
		System.out.println("Idade: " + idade);
		System.out.println("Lendo: " + lendo);
		System.out.println("Livro favorito: " + favorito);
		
	}
	
}
