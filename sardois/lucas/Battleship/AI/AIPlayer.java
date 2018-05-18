package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Player;

public abstract class AIPlayer extends Player {

	public AIPlayer(String name) {
		super(name);
	}
	
	public boolean hasUI() {
		return false;
	}
}
