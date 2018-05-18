package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Player;

public abstract class AI extends Player {

	public AI(String name) {
		super(name);
	}
	
	public boolean hasUI() {
		return false;
	}
}
