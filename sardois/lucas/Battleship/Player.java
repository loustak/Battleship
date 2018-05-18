
package sardois.lucas.Battleship;

import java.util.ArrayList;

public abstract class Player {

	// Player name
	protected String name;
	// All the ship of the player
	protected ArrayList<Ship> fleet;
	// A list of all the coords the player have fired at
	protected ArrayList<Shoot> shoots;

	public Player(String name) {
		fleet = new ArrayList<Ship>();
		shoots = new ArrayList<Shoot>();
		this.name = name;
	}
	
	public final String getName() {
		return name;
	}
	
	public abstract void placeFleet(int shipSizes[]);
	
	public abstract Ship placeShip(int shipSize);

	protected final boolean collide(Ship ship) {
		// Check for every ships in the fleet
		for (Ship otherShip : fleet) {
			if (ship.collide(otherShip)) {
				return true;
			}
		}
		return false;
	}
	
	public abstract Shoot shoot(Player ennemyPlayer);
	
	protected final Shoot shootAt(Player ennemyPlayer, Coord missileCoord) {
		ShootState shootState = ShootState.MISSED;
		for (Ship ship : ennemyPlayer.fleet) {
			shootState = ship.hit(missileCoord);
			if (shootState != ShootState.MISSED) {
				break;
			}
		}
		Shoot shoot = new Shoot(missileCoord, shootState);
		shoots.add(shoot);
		return shoot;
	}

	public final boolean lost() {
		for (Ship ship : fleet) {
			if (!ship.isDestroyed()) {
				return false;
			}
		}
		return true;
	}

	protected final Shoot firedAt(Coord coord) {
		for (Shoot shoot : shoots) {
			if (coord.equals(shoot.getCoord())) {
				return shoot;
			}
		}
		return null;
	}

	protected final boolean isShipAt(Coord coord) {
		for (Ship ship : fleet) {
			for (Coord coordInShip : ship.getCoordArray()) {
				if (coordInShip.equals(coord)) {
					return true;
				}
			}
		}
		return false;
	}
	
	protected abstract boolean hasUI();
	
	protected void reset() {
		
	}
	
	public String grid(boolean displayShips, boolean displayShoots) {
		return "";
	}
	
	public String toString() {
		return getName();
	}
}