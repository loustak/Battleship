package sardois.lucas.Battleship;

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
		
		reset();
		swapPlayer();
		
		// Ask both players to place their fleet
		for (Player player : players) {
			player.placeFleet(shipSizes);
		}
		
		// Game loop
		while (winner == null) {
			currentPlayer = getCurrentPlayer();
			ennemyPlayer = getEnnemyPlayer();
			currentPlayer.shoot(ennemyPlayer);
			
			if (ennemyPlayer.lost()) {
				winner = currentPlayer;
			}
			
			turn++;
			nextTurn();
		}
		
		return turn;
	}	
}
