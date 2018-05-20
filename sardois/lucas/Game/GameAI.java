
package sardois.lucas.Game;

import sardois.lucas.Player.Player;
import sardois.lucas.Player.AI.AIPlayer;

/**
 * A game between AI (and only AI)
 * @author Lucas
 *
 */
public class GameAI extends Game {

	/**
	 * 
	 * @param firstPlayer the AI to start the game
	 * @param secondPlayer the second AI
	 */
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
				loser = ennemyPlayer;
			}
			
			nextTurn();
		}
		
		return turn;
	}	
}
