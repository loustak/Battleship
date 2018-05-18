package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Player;

public abstract class AI extends Player {

	public AI(String name) {
		super(name);
	}

	public String grid(boolean displayShips, boolean displayShoots) {
		return "";
	}
	
	public boolean hasUI() {
		return false;
	}
}
