package aula;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("NEUROMANCER");
		
		// Tipos de vari�veis
		int numero = 24000;
		String frase = "case";
		boolean var = true;
		char var2 = 'l';
		double var3 = 24.9;
		
		System.out.println(numero);
		System.out.println(frase);		
		System.out.println(var);
		System.out.println(var2);
		System.out.println(var3);
		
		// Constantes
		final int vida_maxima = 10;
		System.out.println(vida_maxima);
		
		// Arrays
		String[] nome = new String[5];
		nome[0] = "Wageeeee";
		nome[1] = "Sprawl";
		System.out.println(nome[0]);
		
		// Array MultiDimensional
		String[][] nome_multi = new String[5][5];
		nome_multi[0][0] = "Linda Lee";
		nome_multi[0][1] = "Ratz";
		System.out.println(nome_multi[0][1]);
		
		// Condi��o IF
		String cond = "dek";
		if (cond == "desk") { // == >= <= !=
			System.out.println("desk");
		} else if (cond == "dek") {
			System.out.println(cond);
		}
		
		boolean any = true;
		if (any) System.out.println("Verdade");
		
		// Condi��o Switch e Case
		
		int vida = 100;
		switch(vida)
		{
			
			case 90:
				System.out.println("vida � 90");
			break;
			case 30:
				System.out.println("vida � 30");
			break;
			default:
				System.out.println("vida � 100");
			break;
		
		}
		
		// Operadores
		String nome_jogador = "case";
		int skill = 100;
		
		if((nome_jogador == "case" && skill == 100) && skill <= 90) {
			// podemos executar esse c�digo
			System.out.println("verdade");
		} else {
			System.out.println("entramos no else");
		}
		
		if (nome_jogador == "case" || nome_jogador == "lee") {
			System.out.println("comece o jogo");
		}
		
		
		// Estruturas de repeti��o
		int loopwhile = 1;
		
		while (loopwhile <= 10) {
			System.out.println(loopwhile);
			loopwhile++;
		}
		
		for (int i = 1; i < 10; i++) {
			System.out.println(i);
		}
		
		
		int loopdowhile = 1;
		do {
			System.out.println("n�mero: " + loopdowhile);
			loopdowhile++;
		} while (loopdowhile < 10);
		
		
				
	}
	
}