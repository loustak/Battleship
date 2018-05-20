package sardois.lucas.Player;

import java.util.ArrayList;

import sardois.lucas.Core.Coord;
import sardois.lucas.Core.CoordShoot;
import sardois.lucas.Core.Ship;
import sardois.lucas.Core.ShootState;


public abstract class Player {

	// Player name
	protected String name;
	// All the ship of the player
	protected ArrayList<Ship> fleet;
	// A list of all the coords the player have fired at
	protected ArrayList<CoordShoot> shoots;

	public Player(String name) {
		resetFleet();
		resetShoots();
		this.name = name;
	}
	
	public final String getName() {
		return name;
	}
	
	public final void resetFleet() {
		fleet = new ArrayList<Ship>();
	}
	
	public final void resetShoots() {
		shoots = new ArrayList<CoordShoot>();
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
	
	public abstract CoordShoot shoot(Player ennemyPlayer);
	
	protected final CoordShoot shootAt(Player ennemyPlayer, Coord missileCoord) {
		ShootState shootState = ShootState.MISSED;
		for (Ship ship : ennemyPlayer.fleet) {
			shootState = ship.hit(missileCoord);
			if (shootState != ShootState.MISSED) {
				break;
			}
		}
		CoordShoot shoot = new CoordShoot(missileCoord, shootState);
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

	protected final CoordShoot firedAt(Coord coord) {
		for (CoordShoot shoot : shoots) {
			if (coord.equals(shoot)) {
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
	
	public abstract boolean hasUI();
	
	public void reset() {
		
	}
	
	public String grid(boolean displayShips, boolean displayShoots) {
		return "";
	}
	
	public String toString() {
		return getName();
	}
}