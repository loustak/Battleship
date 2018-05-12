
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
        fleet.add(ship);
    }

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
    protected Ship shipHit(String missileCoordString) throws CoordException {
        Coord missileCoord = new Coord(missileCoordString);
        Ship shipToReturn = null;

        for (Ship ship : getFleet()) {
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
    private boolean shipAt(Coord coord) {
        for (Ship ship : fleet) {
            for (Coord coordInShip : ship.getCoords()) {
                if (coordInShip.equals(coord)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param n is an int corresponding to the number of "space" character to draw
     * @return a String composed of n spaces
     */
    private String addSpace(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * @return a string representing the player's grid
     */
    public String toString() {
        Coord minCoord = Coord.getMinCoord();
        Coord maxCoord = Coord.getMaxCoord();
        // Will be used to move inside the grid
        Coord currentCoord;

        // Compute the width & height of the grid
        int width = maxCoord.getCoord1() - minCoord.getCoord1() + 1;
        int height = maxCoord.getCoord2() - minCoord.getCoord2() + 1;
        // Get the starting letter of the grid
        int minLetter = minCoord.getCoord1();
        // Get the starting number of the grid
        int minNumber = minCoord.getCoord2();
        // Compute the maximum length of a coordinate as a string, 
        // to properly align the grid
        int maxCoordStringLength = String.valueOf(maxCoord.getCoord2()).length();

        String numberString;
        StringBuilder stringBuilder = new StringBuilder();

        // Draw the letter coords line
        stringBuilder.append(addSpace(maxCoordStringLength + 1));
        for (int i = 0; i < width; i++) {
            stringBuilder.append(String.valueOf((char)(minLetter + i)));
            stringBuilder.append(" ");
        }
        stringBuilder.append("\n");

        // Draw the rest of the grid
        for (int i = 0; i < height; i++) {
            numberString = Integer.toString(minNumber + i);
            // Add the current number
            stringBuilder.append(numberString);
            // Add enough spaces to align correctly the grid
            stringBuilder.append(addSpace(maxCoordStringLength - numberString.length() + 1));

            for (int j = 0; j < width; j++) {
                // Get the current coordinate
                currentCoord = new Coord((char)(minLetter + j), i + 1);

                Coord fired = firedAt(currentCoord);
                if (fired != null) {
                    if (fired.isHit()) {
                        stringBuilder.append("x");
                    } else {
                        stringBuilder.append("*");
                    }
                }
                if (shipAt(currentCoord)) {
                    stringBuilder.append("o");
                } else {
                    stringBuilder.append(".");
                }

                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}