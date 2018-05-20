package sardois.lucas.Game;

import sardois.lucas.Core.CoordShoot;
import sardois.lucas.Player.Player;

/**
 * A game where between each action we will update UI
 * @author Lucas
 *
 */
public class GameUI extends Game {

	/**
	 * 
	 * @param firstPlayer the player to start the game
	 * @param secondPlayer the second player
	 */
	public GameUI(Player firstPlayer, Player secondPlayer) {
		super(firstPlayer, secondPlayer);
	}

	public int play() {
		Player currentPlayer;
		Player ennemyPlayer;
		winner = null;
		
		for (Player player : players) {
			if (player.hasUI()) {
				System.out.println(player + " place your ships on the grid.");
				System.out.println(player.grid(false, false));
			}
			player.placeFleet(GameRule.shipSizes);
		}
		
		while (getWinner() == null) {
			currentPlayer = getCurrentPlayer();
			ennemyPlayer = getEnnemyPlayer();
			
			if (currentPlayer.hasUI()) {
				System.out.println(currentPlayer + " your grid of previous shoot: ");
				System.out.println(currentPlayer.grid(false, true));
			}
			System.out.print("Enter the coordinate to shoot at: ");
			CoordShoot shoot = currentPlayer.shoot(ennemyPlayer);
			System.out.println(currentPlayer + " shoot at " + shoot + ".");
			
			if (ennemyPlayer.lost()) {
				winner = currentPlayer;
				loser = ennemyPlayer;
				System.out.println(winner + " win the game!");
			}
			
			nextTurn();
		}
		
		return turn;
	}
}
