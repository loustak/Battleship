
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The abstract player class
 */
class Player {

    private int number;
    private ArrayList<Ship> fleet;

    public Player(int number) {
        this.number = number;
    }

    /**
     * @param startCoord is a String representing the start coordinate of the ship
     * @param endCoord is a String representing the end coordinate of the ship
     * @throws CoordException an Exception occured when creating the Coord 
     * @throws ShipException an Exception occured when creating the Ship
     */
    public void placeShip(String startCoord, String endCoord) throws CoordException, ShipException {
        Ship ship = new Ship(startCoord, endCoord);
        // Does the sheep collide with any others ships ?
        if (collide(ship)) {
            throw new ShipExceptionCollide();
        }
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
}