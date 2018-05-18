package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Game;
import sardois.lucas.Battleship.GameAI;

public class AIProof {

	public static void main(String[] args) {
		
		AIPlayer player1 = new MediumAI();
		AIPlayer player2 = new BeginnerAI();
		
		Game game = new GameAI(player1, player2);
		int games = 100;
		int wins = 0;
		
		for (int i = 0; i < games; i++) {
			int turn = game.play();
			if (game.getWinner() == player1) {
				wins++;
			}
		}
		
		System.out.println(player1.getName() + " won " + wins + "% of the matches against " + player2.getName());
	}
}
