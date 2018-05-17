package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Coord;
import sardois.lucas.Battleship.Player;
import sardois.lucas.Battleship.Ship;
import sardois.lucas.Battleship.ShipCollideException;
import sardois.lucas.Battleship.Util.Random;

public class BegginerAI extends Player implements IAI {
	
	public BegginerAI() {
		super("Begginer AI");
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
	protected Ship placeShip(int shipSize) {
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
		
		System.out.println(shipToReturn);
		
		return shipToReturn;
	}

	@Override
	public void shoot(Player ennemyPlayer) {
		Coord randomCoord = Coord.getRandomCoord(0);
		shootAt(ennemyPlayer, randomCoord);
	}
}
