
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The abstract player class
 */
abstract class Player {

    protected String name; // Player name
    protected ArrayList<Ship> fleet;
    protected Grid grid; // Every player have a grid just in case if we want to see the AI's grid

    public Player() {
        fleet = new ArrayList<Ship>();
        grid = new Grid();
    }

    /**
     * @return the player name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Place all the ships one by one
     * @param shipSizes are the differents ships sizes to place
     */
    public abstract void placeFleet(int[] shipSizes);

    /**
     * @param startCoord is a String representing the start coordinate of the ship
     * @param endCoord is a String representing the end coordinate of the ship
     * @throws CoordException means an error occured when creating the Coord 
     * @throws ShipException means an error occured when creating the Ship
     */
    protected void addShip(String startCoord, String endCoord, int size) throws CoordException, ShipException {
        Ship ship = new Ship(startCoord, endCoord);
        // Does the sheep collide with any others ships ?
        if (collide(ship)) {
            throw new ShipExceptionCollide();
        }
        if (Math.abs(ship.getCoords().length) != size) {
            throw new ShipExceptionSize(size);
        }
        // Add the ship to the fleet
        fleet.add(ship);
        // Update the grid
        grid.addShip(ship);
    }

    /**
     * @param ship is the ship to test collide with
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
     * @param ennemy is the ennemy Player to shoot at
     */
    public abstract void shoot(Player ennemy);

    /**
     * @param coord is a String designating the location to shoot at
     * @return the Ship touched by the missile. If no ship where touched return null
     */
    protected Ship shipHit(Player ennemy, String missileCoordString) throws CoordException {
        Coord missileCoord = new Coord(missileCoordString);
        for (Ship ship : ennemy.getFleet()) {
            if (ship.hit(missileCoord)) {
                grid.addShoot(missileCoord, true);
                return ship;
            }
        }
        grid.addShoot(missileCoord, false);
        return null;
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
     * Display the player's grid
     */
    public void drawGrid() {
        grid.draw();
    }
}