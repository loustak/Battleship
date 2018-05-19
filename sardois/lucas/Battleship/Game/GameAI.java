package sardois.lucas.Battleship.Game;

import sardois.lucas.Battleship.Player.Player;
import sardois.lucas.Battleship.Player.AI.AIPlayer;

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
			player.placeFleet(GameRule.shipSizes);
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
