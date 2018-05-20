package fr.battleship;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import sardois.lucas.Game.Game;
import sardois.lucas.Game.GameAI;
import sardois.lucas.Player.AI.AIPlayer;
import sardois.lucas.Player.AI.BeginnerAI;
import sardois.lucas.Player.AI.HardAI;
import sardois.lucas.Player.AI.MediumAI;

/**
 * This class play games between the differents levels of AI
 * @author Lucas
 *
 */
public class TestIA {
	
	static private final int games = 100;
	static private final String fileName = "ai_proof.csv";

	/**
	 * The main method
	 * @param args the arguments to pass
	 */
	public static void main(String[] args) {
		
		int winBeginnerVsMedium = TestIA.fight(new BeginnerAI(), new MediumAI());
		int winBeginnerVsHard = TestIA.fight(new BeginnerAI(), new HardAI());
		int winMediumVsHard = TestIA.fight(new MediumAI(), new HardAI());
		
		FileWriter file = null;
		BufferedWriter writer = null;
		
		try {
			file = new FileWriter(fileName);
			writer = new BufferedWriter(file); 
		    writer.write("AI Name; score; AI Name2; score2\n");
		    writer.write("AI Level Beginner;" + winBeginnerVsMedium +  "; Level Medium;" + (games - winBeginnerVsMedium) + "\n");
		    writer.write("AI Level Beginner;" + winBeginnerVsHard +  "; Level Hard;" + (games - winBeginnerVsHard) + "\n");
		    writer.write("AI Level Medium;" + winMediumVsHard +  "; Level Hard;" + (games - winMediumVsHard) + "\n");
		} catch (IOException exception) {
		    System.out.println("Can't write file \"" + fileName + "\": " + exception);
		} finally {
			try {
				writer.close();
			} catch (IOException exception) {
				System.out.println("Can't write file \"" + fileName + "\": " + exception);
			}
			System.out.println("File saved as " + fileName);
		}
	}

	static int fight(AIPlayer ai1, AIPlayer ai2) {
		Game game = new GameAI(ai1, ai2);
		int win = 0;
		
		for (int i = 0; i < games; i++) {
			game.play();
			if (game.getWinner() == ai1) {
				win++;
			}
			game.reset();
		}
		
		return win;
	}
}