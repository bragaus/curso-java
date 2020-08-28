package programa_leitura;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
//		System.out.println("Por favor digite o seu nome:");
//		String nome = in.nextLine();
//		
//		System.out.println("Por favor digite o sua idade:");
//		String idade = in.nextLine();
//		
//		System.out.println("Por favor digite o livro que você está lendo:");
//		String lendo = in.nextLine();
//		
//		if(lendo.equals("Neuromancer")) {
//			System.out.println("Show! também estou lendo esse livro");
//		} else {
//			System.out.println("VC PRECISAR LER NEUROMANCER AAAAAA");
//		}
//		
//		System.out.println("Por favor digite o seu livro favorito:");
//		String favorito = in.nextLine();
//		
//		System.out.println("-----------------------------------");
//		System.out.println("O resultado é:");
//		System.out.println("Nome: " + nome);
//		System.out.println("Idade: " + idade);
//		System.out.println("Lendo: " + lendo);
//		System.out.println("Livro favorito: " + favorito);
		
		Scanner in = new Scanner(System.in);
		String nome;
		
		Random rand = new Random();
		
		System.out.println("Seja bem-vindo ao jogo! Insira seu nome:");
		nome = in.nextLine();
		
		System.out.println("Seja muito bem vindo(a) " + nome);
		System.out.println("Você deseja avançar para onde? (w,s,d,a)");
		String comando = in.nextLine();
		
		if (comando.equals("w")) {
			System.out.println("Você encontra uma arma oque você faz? (a = pegar) (w = andar)");
			comando = in.nextLine();
			if (comando.equals("a")) {
				System.out.println("Você pegou a arma oque você faz? (a = atirar) (w = andar)");
				comando = in.nextLine();
				if (rand.nextInt(100) < 50) {
					System.out.println("Você atirou na sua cabeça!");
				} else {
					System.out.println("Você atirou no escuro");
				}
			}
		}
		
		System.out.println("Fim de jogo");
	}
	
}
