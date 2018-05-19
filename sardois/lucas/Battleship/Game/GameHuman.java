package sardois.lucas.Battleship.Game;

import sardois.lucas.Battleship.Player;
import sardois.lucas.Battleship.Shoot;

public class GameHuman extends Game {

	public GameHuman(Player firstPlayer, Player secondPlayer) {
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
			player.placeFleet(shipSizes);
		}
		
		while (getWinner() == null) {
			currentPlayer = getCurrentPlayer();
			ennemyPlayer = getEnnemyPlayer();
			
			if (currentPlayer.hasUI()) {
				System.out.println(currentPlayer + " your grid of previous shoot: ");
				System.out.println(currentPlayer.grid(false, true));
			}
			System.out.print("Enter the coordinate to shoot at: ");
			Shoot shoot = currentPlayer.shoot(ennemyPlayer);
			switch (shoot.getShootState()) {
				case MISSED:
					System.out.println(currentPlayer + " missed his shoot at " + shoot.getCoord() + ".");
					break;
				case TOUCHED:
					System.out.println(currentPlayer + " touched a ship at " + shoot.getCoord() + ".");
					break;
				case SINK:
					System.out.println(currentPlayer + " sinked a ship at " + shoot.getCoord() + ".");
					break;
			}
			
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
