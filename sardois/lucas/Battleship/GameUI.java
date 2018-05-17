package sardois.lucas.Battleship;

public class GameUI extends Game {

	public GameUI(Player firstPlayer, Player endPlayer) {
		super(firstPlayer, endPlayer);
	}

	@Override
	public Player play() {
		Player winner = null;
		
		// Ask both players to place their fleet
		System.out.println(currentPlayer.getName() + " place your ships:");
		currentPlayer.placeFleet(shipSizes);
		
		
		ennemyPlayer.placeFleet(shipSizes);
		
		// Game loop
		while (winner == null) {
			currentPlayer.shoot(ennemyPlayer);
			
			if (ennemyPlayer.lost()) {
				winner = currentPlayer;
			}
			
			swapPlayer();
		}
		
		return winner;
	}
	
	
}
