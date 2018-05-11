
/**
 * A ship is a collection of coordinates
 */
public class Ship {

    // Coord array representing the positions of the ship
    private Coord[] coords;

    /**
     * @param startCoord a String indicating the start coordinate of the ship
     * @param endCoord a String indicating the end coordinate of the ship
     * @throws CoordException [description]
     */
    public Ship(String startCoordString, String endCoordString) throws CoordException, ShipException {
        // Try to convert String coord to real Coordinate
        Coord start = new Coord(startCoordString);
        Coord end = new Coord(endCoordString);

        boolean isHorizontal = isHorizontal(start, end);
        int length = length(start, end, isHorizontal);

        // Construct the coords array, length can be vertical if the ship is placed backward
        coords = new Coord[Math.abs(length) + 1];

        // Add all the intermediates coords in the array
        for (int i = 0; i < coords.length; i++) {
            // Math.signum return the sign of the variable
            int offset = (int) (i * Math.signum(length));
            // Add all the intermediate coords between the start coord and the end coord
            if (isHorizontal) {
                coords[i] = new Coord(start.getCoord1() + offset, start.getCoord2());
            } else {
                coords[i] = new Coord(start.getCoord1(), start.getCoord2() + offset);
            }
        }
    }

    /**
     * @return a boolean set to true if the ship is horizontal and to false if it's vertical
     * @throws ShipExceptionHorizontal an exception saying that the 
     * ship must be either vertical or horizontal
     */
    private boolean isHorizontal(Coord start, Coord end) throws ShipExceptionHorizontal {
        if (start.getCoord2() == end.getCoord2() && start.getCoord1() != end.getCoord1()) {
            return true;
        } else if (start.getCoord1() == end.getCoord1() && start.getCoord2() != end.getCoord2()) {
            return false;
        } else {
            throw new ShipExceptionHorizontal();
        }
    }

    /**
     * @param isHorizontal is a boolean set to true if the ship is placed horizontally 
     * and set to false if the ship is placed vertically
     * @return the length of the ship
     */
    private int length(Coord start, Coord end, boolean isHorizontal) {
        if (isHorizontal) {
            return end.getCoord1() - start.getCoord1();
        } else {
            return end.getCoord2() - start.getCoord2();
        }
    }

    /**
     * @return an array of Coord
     */
    public Coord[] getCoords() {
        return coords;
    }

    /**
     * @param missileCoord is a String representing the coordinate to check
     * @return a boolean set to true if the missile touched this ship, else return false
     */
    public boolean isHit(String missileCoord) throws CoordException {
        Coord coord = new Coord(missileCoord);

        for (int i = 0; i < coords.length; i++) {
            if (coords[i].equals(coord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hit the ship at the designated coordinate
     * @param missileCoord is the coordinate to hit
     * @return a boolean set to true if the ship was touched, else return false
     */
    public boolean hit(Coord missileCoord) throws CoordException {
        for (int i = 0; i < coords.length; i++) {
            if (coords[i].equals(missileCoord)) {
                coords[i].setHit();
                return true;
            }
        }
        return false;
    }

    /**
     * @return a boolean set to true if the ship as sink, else return false
     */
    public boolean isDestroyed() {
        for (int i = 0; i < coords.length; i++) {
            if (!coords[i].isHit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param ship is the ship to test
     * @return a boolean set to true if the ship collide with any other ship, else return false
     */
    public boolean collide(Ship ship) {
        // For each of the ship coords
        for (Coord thisCoord : this.getCoords()) {
            // Check if any coords is equals to the other ship coords
            for (Coord otherCoord : ship.getCoords()) {
                if (thisCoord.equals(otherCoord)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return a String representation of the ship composed of all it's coordinates
     */
    public String toString() {
        String str = "";
        
        for (int i = 0; i < coords.length; i++) {
            str += coords[i].toString() + " ";
        }
        return str;
    }
}
