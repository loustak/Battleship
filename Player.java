
package sardois.lucas.Player;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The abstract player class
 */
class Player {

    // Player name
    protected String name;
    // All the ship of the player
    protected ArrayList<Ship> fleet;
    // A list of all the coords the player have fired at
    protected ArrayList<Coord> fired;

    public Player(String name) {
        fleet = new ArrayList<Ship>();
        fired = new ArrayList<Coord>();
        this.name = name;
    }

    /**
     * @return the player name as a String
     */
    public String getName() {
        return name;
    }

    /**
    * @param shipSizes is an int array representing all the ships sizes
    */
    public abstract void placeFleet(int shipSizes[]) {
        for (int size : shipSizes) {
            fleet.add(addShip());
        }
    }

    /**
    * Ask the player for a ship 
    */
    public abstract Ship addShip(int shipSize);

    /**
     * @param ship is the Ship to test the collide with
     * @return a boolean set to true if the ship collide with any other ship, else return false
     */
    private boolean collide(Ship ship) {
        // Check for every ships in the fleet
        for (Ship otherShip : fleet) {
            if (ship.collide(otherShip)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return an ArrayList of Ship representing the fleet of the player
     */
    private ArrayList<Ship> getFleet() {
        return fleet;
    }

    /**
     * @param coord is a String designating the location to shoot at
     * @return the Ship touched by the missile. If no ship where touched return null
     */
    protected Ship shipHit(Player ennemyPlayer, String missileCoordString) throws CoordException {
        Coord missileCoord = new Coord(missileCoordString);
        Ship shipToReturn = null;

        for (Ship ship : ennemyPlayer.getFleet()) {
            if (ship.hit(missileCoord)) {
                // The shoot have touched something
                missileCoord.setHit();
                shipToReturn = ship;
                break;
            }
        }
        fired.add(missileCoord);
        return shipToReturn;
    }

    /**
     * @return a boolean set to true if the player have lost, ie: all it's ships are destroyed
     */
    public boolean lost() {
        for (Ship ship : fleet) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param  coord is a Coord object representing the coord to check 
     * @return a Coord object if there was a shoot at coord, else return null
     */
    private Coord firedAt(Coord coord) {
        for (Coord coordInList : fired) {
            if (coord.equals(coordInList)) {
                return coordInList;
            }
        }
        return null;
    }

    /**
     * @param  coord is a Coord object representing the coord to check 
     * @return a boolean set to true if there is a ship at the coord position, else return false
     */
    private boolean isShipAt(Coord coord) {
        for (Ship ship : fleet) {
            for (Coord coordInShip : ship.getCoordArray()) {
                if (coordInShip.equals(coord)) {
                    return true;
                }
            }
        }
        return false;
    }
}