package sardois.lucas.Battleship.Game;

import sardois.lucas.Battleship.Player;
import sardois.lucas.Battleship.AI.AIPlayer;

public class GameAI extends Game {

	public GameAI(AIPlayer firstPlayer, AIPlayer secondPlayer) {
		super(firstPlayer, secondPlayer);
	}

	@Override
	public int play() {
		Player currentPlayer;
		Player ennemyPlayer;
		winner = null;
		
		for (Player player : players) {
			player.placeFleet(shipSizes);
		}
		
		while (getWinner() == null) {
			currentPlayer = getCurrentPlayer();
			ennemyPlayer = getEnnemyPlayer();
			currentPlayer.shoot(ennemyPlayer);
			
			if (ennemyPlayer.lost()) {
				winner = currentPlayer;
			}
			
			nextTurn();
		}
		
		return turn;
	}	
}
