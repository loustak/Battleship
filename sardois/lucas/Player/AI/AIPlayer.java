package sardois.lucas.Player.AI;

import sardois.lucas.Core.Coord;
import sardois.lucas.Core.Ship;
import sardois.lucas.Core.ShipCollideException;
import sardois.lucas.Player.Player;
import sardois.lucas.Util.Util;

/**
 * 
 * @author Lucas
 * This class aim to modelise a AI player playing a Battleship game
 */
public abstract class AIPlayer extends Player {

	/**
	 * Construct a new AIPlayer
	 * @param name the name to give to the AI
	 */
	public AIPlayer(String name) {
		super(name);
	}
	
	public final boolean hasUI() {
		return false;
	}
	
	@Override
	public void placeFleet(int[] shipSizes) {
		Ship ship;

		for (int size : shipSizes) {
			ship = placeShip(size);
			if (collide(ship)) {
				throw new ShipCollideException();
			}
			fleet.add(ship);
		}
	}

	@Override
	public Ship placeShip(int shipSize) {
		Ship shipToReturn = null;
		Coord startCoord;
		boolean isHorizontal;

		while (shipToReturn == null) {
			startCoord = Coord.getRandomCoord(shipSize);
			isHorizontal = Util.randomRange(0, 1) == 0;

			shipToReturn = new Ship(startCoord, isHorizontal, shipSize);

			if (collide(shipToReturn)) {
				shipToReturn = null;
			}
		}
		return shipToReturn;
	}
}
