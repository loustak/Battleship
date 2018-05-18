package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Game;
import sardois.lucas.Battleship.GameAI;

public class AIProof {

	public static void main(String[] args) {
		
		AIPlayer player1 = new BeginnerAI();
		AIPlayer player2 = new BeginnerAI();
		
		Game game = new GameAI(player1, player2);
		int games = 100;
		int wins = 0;
		
		for (int i = 0; i < games; i++) {
			game.play();
			if (game.getWinner() == player1) {
				wins++;
			}
			System.out.println("Winner is " + (AIPlayer)game.getWinner());
			game = new GameAI(player1, player2);
		}
		
		int winPercentage = (int)((((float) wins / (float)games)) * 100);
		System.out.println(player1.getName() + " won " + winPercentage + "% of the matches against " + player2.getName());
	}
}
