package sardois.lucas.Game;

import sardois.lucas.Player.Player;

/**
 * The Game class is used to represent two player playing a game of
 * battleship
 * @author Lucas
 *
 */
public abstract class Game {

	protected Player[] players;
	protected Player winner = null;
	protected Player loser = null;
	protected int currentPlayerIndex = 0;
	protected int turn = 1;
	
	/**
	 * Create a new game composed of two player
	 * @param firstPlayer the player to start
	 * @param secondPlayer the second player
	 */
	public Game(Player firstPlayer, Player secondPlayer) {
		players = new Player[2];
		players[0] = firstPlayer;
		players[1] = secondPlayer;
	}
	
	protected void nextTurn() {
		currentPlayerIndex++;
		if (currentPlayerIndex >= players.length) {
			currentPlayerIndex = 0;
			turn++;
		}
	}
	
	protected Player getEnnemyPlayer() {
		if (currentPlayerIndex == 0) {
			return players[1];
		}
		return players[0];
	}
	
	protected Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}
	
	/**
	 * 
	 * @return the winner of the game if the game is finished, else return null
	 */
	public Player getWinner() {
		return winner;
	}
	
	/**
	 * 
	 * @return the loset of the game if the game is finished, else return null
	 */
	public Player getLoser() {
		return loser;
	}
	
	/**
	 * Start the game of battleship
	 * @return the number of turn the game lasted
	 */
	public abstract int play();
	
	/**
	 * Place the game in it's initial state as if it where newly constructed 
	 * except that it switch players order
	 */
	public void reset() {
		turn = 1;
		currentPlayerIndex = 0;
		
		for (Player player : players) {
			player.resetFleet();
			player.resetShoots();
			player.reset();
		}
		
		Player winner = getWinner();
		Player loser = getLoser();
		
		if (winner != null && loser != null) {
			players[0] = loser;
			players[1] = winner;
		}
	}
}
