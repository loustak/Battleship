package sardois.lucas.Battleship;

public class Game {

	protected Player[] players;
	protected int currentPlayerIndex = 0;
	protected int[] shipSizes = {/*5, 4, 3, 3,*/ 2};
	
	public Game(Player firstPlayer, Player secondPlayer) {
		players = new Player[2];
		players[0] = firstPlayer;
		players[1] = secondPlayer;
	}
	
	protected void swapPlayer() {
		currentPlayerIndex++;
		if (currentPlayerIndex >= players.length) {
			currentPlayerIndex = 0;
		}
	}
	
	private Player getEnnemyPlayer() {
		if (currentPlayerIndex == 0) {
			return players[1];
		}
		return players[0];
	}
	
	private Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}
	
	private boolean shouldDisplayUI() {
		for (Player player : players) {
			if (player.hasUI()) {
				return true;
			}
		}
		return false;
	}
	
	public Player play() {
		Player winner = null;
		Player currentPlayer;
		Player ennemyPlayer;
		
		// Ask both players to place their fleet
		for (Player player : players) {
			if (player.hasUI()) {
				System.out.println(player.getName() + " place your ships on the grid.");
				System.out.println(player.grid(false, false));
			}
			player.placeFleet(shipSizes);
		}
		
		// Game loop
		while (winner == null) {
			currentPlayer = getCurrentPlayer();
			ennemyPlayer = getEnnemyPlayer();
			
			if (currentPlayer.hasUI()) {
				System.out.println(currentPlayer.getName() + " your grid of previous shoot: ");
				System.out.println(currentPlayer.grid(false, true));
				System.out.print("Enter the coordinate to shoot at: ");
			}
			Shoot shoot = currentPlayer.shoot(ennemyPlayer);
			if (shouldDisplayUI()) {
				switch (shoot.getShootState()) {
					case MISSED:
						System.out.println(currentPlayer.getName() + " missed his shoot at " + shoot.getCoord() + ".");
						break;
					case TOUCHED:
						System.out.println(currentPlayer.getName() + " touched a ship at " + shoot.getCoord() + ".");
						break;
					case SINK:
						System.out.println(currentPlayer.getName() + " sinked a ship at " + shoot.getCoord() + ".");
						break;
				}
			}
			
			if (ennemyPlayer.lost()) {
				winner = currentPlayer;
				if (shouldDisplayUI()) {
					System.out.println(winner.getName() + " win the game !");
				}
			}
			
			swapPlayer();
		}
		
		return winner;
	}
}
