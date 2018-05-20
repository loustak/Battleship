package sardois.lucas.Player;

import java.util.ArrayList;

import sardois.lucas.Core.Coord;
import sardois.lucas.Core.CoordShoot;
import sardois.lucas.Core.Ship;
import sardois.lucas.Core.ShootState;

/**
 * An abstract class representing a player player a game of battleship
 * @author Lucas
 *
 */
public abstract class Player {

	// Player name
	protected String name;
	// All the ship of the player
	protected ArrayList<Ship> fleet;
	// A list of all the coords the player have fired at
	protected ArrayList<CoordShoot> shoots;

	/**
	 * Construct a new player
	 * @param name the name of the player
	 */
	public Player(String name) {
		resetFleet();
		resetShoots();
		this.name = name;
	}
	
	/**
	 * 
	 * @return the name of the player
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * Empty the player's fleet
	 */
	public final void resetFleet() {
		fleet = new ArrayList<Ship>();
	}
	
	/**
	 * Empty the player's previous shoots list
	 */
	public final void resetShoots() {
		shoots = new ArrayList<CoordShoot>();
	}
	
	/**
	 * Ask the player to place each ship of his fleet
	 * @param shipSizes the differents ships sizes to place
	 */
	public abstract void placeFleet(int shipSizes[]);
	
	/**
	 * Ask the player to place one ship
	 * @param shipSize the size of the ship to place
	 * @return a Ship to place
	 */
	public abstract Ship placeShip(int shipSize);

	/**
	 * 
	 * @param ship the ship to check the collision
	 * @return true if the given ship collide with any other ship of 
	 * player's fleet, else return false
	 */
	protected final boolean collide(Ship ship) {
		// Check for every ships in the fleet
		for (Ship otherShip : fleet) {
			if (ship.collide(otherShip)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param ennemyPlayer the player to shoot at
	 * @return the result of the shoot
	 */
	public abstract CoordShoot shoot(Player ennemyPlayer);
	
	/**
	 * 
	 * @param ennemyPlayer the player to shoot at
	 * @param missileCoord the coordinate to shoot at
	 * @return the result of the shoot
	 */
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

	/**
	 * 
	 * @return true if the player lost. A player have lost when all it's ship are sinked
	 */
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
	
	/**
	 * 
	 * @return does this player need UI to interact ?
	 */
	public abstract boolean hasUI();
	
	/**
	 * Player can redefine this function if they need to do something special when
	 * restarting a game
	 */
	public void reset() {
		
	}
	
	/**
	 * 
	 * @param displayShips should we display the ships of the player on the grid?
	 * @param displayShoots should we display the shoots of the player on the grid?
	 * @return a grid depending of the parameters
	 */
	public String grid(boolean displayShips, boolean displayShoots) {
		return "";
	}
	
	public String toString() {
		return getName();
	}
}