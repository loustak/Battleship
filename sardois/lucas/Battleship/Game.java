package sardois.lucas.Battleship;

public class Game {

	protected Player currentPlayer;
	protected Player ennemyPlayer;
	protected int[] shipSizes = {/*5, 4, 3, 3,*/ 2};
	
	public Game(Player firstPlayer, Player endPlayer) {
		this.currentPlayer = firstPlayer;
		this.ennemyPlayer = endPlayer;
	}
	
	public void swapPlayer() {
		Player currentPlayerSaved = currentPlayer;
		currentPlayer = ennemyPlayer;
		ennemyPlayer = currentPlayerSaved;
	}
	
	public Player play() {
		Player winner = null;
		
		// Ask both players to place their fleet
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
	};
}