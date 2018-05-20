package sardois.lucas.Battleship.Game;

import sardois.lucas.Battleship.Core.CoordShoot;
import sardois.lucas.Battleship.Player.Player;

public class GameUI extends Game {

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
