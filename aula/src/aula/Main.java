package aula;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("NEUROMANCER");
		
		// Tipos de variáveis
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
		
		// Condições
		String cond = "dek";
		if (cond == "desk") { // == >= <= !=
			System.out.println("desk");
		} else if (cond == "dek") {
			System.out.println(cond);
		}
		
		boolean any = true;
		if (any) System.out.println("Verdade");
		
		
		
				
	}
	
}