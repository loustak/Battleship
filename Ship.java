
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
    public Ship(Coord start, int orientation, int length) {
        // Construct the coords array, length can be vertical if the ship is placed backward
        coords = new Coord[Math.abs(length)];

        // Add all the intermediates coords in the array
        for (int i = 0; i < coords.length; i++) {
            // Math.signum return the sign of the variable
            int offset = (int) (i * Math.signum(length));
            // Add all the intermediate coords between the start coord and the end coord
            if (isHorizontal) {
                coords[i] = new Coord(start.getCoordHorizontal() + offset, start.getCoordVertical());
            } else {
                coords[i] = new Coord(start.getCoordHorizontal(), start.getCoordVertical() + offset);
            }
        }
    }

    private static int getOrientation(Coord start, Coord end) {
        if (start.getCoordHorizontal() != end.getCoordHorizontal() && start.getCoordVertical() == end.getCoordVertical()) {
            return 1;
        } else if (start.getCoordVertical() != end.getCoordVertical() && start.getCoordHorizontal() == end.getCoordHorizontal()) {
            return 0;
        }
        return -1;
    }

    public static int length(Coord start, Coord end, boolean isHorizontal) {
        if (isHorizontal) {
            return end.getCoordHorizontal() - start.getCoordHorizontal();
        } else {
            return end.getCoordVertical() - start.getCoordVertical();
        }
    }

    /**
     * @return a int corresponding to the length of the ship.
     */
    public int length() {
        return getCoordArray().length;
    }

    /**
     * @return an array of Coord
     */
    public Coord[] getCoordArray() {
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
        for (Coord thisCoord : this.getCoordArray()) {
            // Check if any coords is equals to the other ship coords
            for (Coord otherCoord : ship.getCoordArray()) {
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
