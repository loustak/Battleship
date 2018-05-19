package sardois.lucas.Battleship.Player.AI;

import sardois.lucas.Battleship.Core.Coord;
import sardois.lucas.Battleship.Core.Ship;
import sardois.lucas.Battleship.Core.ShipCollideException;
import sardois.lucas.Battleship.Player.Player;
import sardois.lucas.Util.Util;

public abstract class AIPlayer extends Player {

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
