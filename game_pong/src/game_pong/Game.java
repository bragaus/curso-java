package game_pong;

import java.awt.Canvas;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setResizable(false); // Usuário não pode redimensionar a janela.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Quando clicar no fechar da janela, vai finalizar toda a aplicação
		
		frame.pack();
		frame.setVisible(true);
		
	}

	public void run() {
		
		
	}

}
