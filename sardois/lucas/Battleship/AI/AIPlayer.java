package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Coord;
import sardois.lucas.Battleship.Player;
import sardois.lucas.Battleship.Ship;
import sardois.lucas.Battleship.ShipCollideException;
import sardois.lucas.Battleship.Util.Random;

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
			isHorizontal = Random.randomRange(0, 1) == 0;

			shipToReturn = new Ship(startCoord, isHorizontal, shipSize);

			if (collide(shipToReturn)) {
				shipToReturn = null;
			}
		}
		return shipToReturn;
	}
}
