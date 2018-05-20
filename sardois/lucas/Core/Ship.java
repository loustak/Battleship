package sardois.lucas.Core;

/**
 * A ship is an array of coordinates
 * @author Lucas
 *
 */
public class Ship {

    // Coord array representing the positions of the ship
    private CoordShoot[] coords;

    /**
     * Construct a new ship. If something goes wrong a checked exception is thrown
     * @param start the start coordinate of the ship
     * @param isHorizontal is the ship horizontal or vertical?
     * @param length the length of the ship
     */
    public Ship(Coord start, boolean isHorizontal, int length) {
        // Construct the coords array, length can be vertical if the ship is placed backward
        coords = new CoordShoot[Math.abs(length)];

        // Add all the intermediates coords in the array
        for (int i = 0; i < coords.length; i++) {
            // Math.signum return the sign of the variable
            int offset = (int) (i * Math.signum(length));
            // Add all the intermediate coords between the start coord and the end coord
            if (isHorizontal) {
                coords[i] = new CoordShoot(start.getCoordHorizontal() + offset, start.getCoordVertical());
            } else {
                coords[i] = new CoordShoot(start.getCoordHorizontal(), start.getCoordVertical() + offset);
            }
        }
    }

    /**
     * 
     * @param start the start coordinate
     * @param end the end coordinate
     * @return 0 if the line between the start and end coordinate is horizontal, 
     * 1 if the line is vertical and 0 if it's both or none
     */
    public static int getOrientation(Coord start, Coord end) {
        if (start.getCoordHorizontal() != end.getCoordHorizontal() && start.getCoordVertical() == end.getCoordVertical()) {
            return 0;
        } else if (start.getCoordVertical() != end.getCoordVertical() && start.getCoordHorizontal() == end.getCoordHorizontal()) {
            return 1;
        }
        return -1;
    }

    /**
     * 
     * @param start the start position of the ship
     * @param end the end position of the ship
     * @param isHorizontal is the ship horizontal or vertical
     * @return the length between the two coordinate. It can be negative
     */
    public static int length(Coord start, Coord end, boolean isHorizontal) {
    	int length;
        if (isHorizontal) {
            length = end.getCoordHorizontal() - start.getCoordHorizontal();
        } else {
            length = end.getCoordVertical() - start.getCoordVertical();
        }
        return length + (int)Math.signum(length);
    }

    /**
     * 
     * @return get all coordinates composing this ship
     */
    public Coord[] getCoordArray() {
        return coords;
    }
    
    /**
     * 
     * @return the length of the ship
     */
    public int length() {
        return getCoordArray().length;
    }

    /**
     * 
     * @param missileCoordString the position to check for
     * @return true if it hit the ship, false else
     */
    public boolean isHit(String missileCoordString) {
        int horizontal = Coord.getHorizontalPartFromString(missileCoordString);
        int vertical = Coord.getVerticalPartFromString(missileCoordString);
        Coord missileCoord = new Coord(horizontal, vertical);

        for (int i = 0; i < coords.length; i++) {
            if (coords[i].equals(missileCoord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Try to hit the ship at the designate coordinate and return the result 
     * @param missileCoord the coordinate to check for
     * @return a new ShootState corresponding to the result of the shoot
     */
    public ShootState hit(Coord missileCoord) {
        for (int i = 0; i < coords.length; i++) {
        	Coord coordToCheck = (Coord) coords[i]; 
            if (coordToCheck.equals(missileCoord)) {
                coords[i].setShootState(ShootState.TOUCHED);
                if (isDestroyed()) {
                	return ShootState.SINK;
                } else {
                	return ShootState.TOUCHED;
                }
            }
        }
        return ShootState.MISSED;
    }

    /**
     * 
     * @return true if the ship is destroyed. A ship is destroyed when 
     * all it's coordinates have been hit
     */
    public boolean isDestroyed() {
        for (int i = 0; i < coords.length; i++) {
            if (coords[i].getShootState() == ShootState.NOT_SHOOT) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param ship the ship to check for
     * @return true if this ship and the given ship collide either return false
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

    public String toString() {
        String str = "";
        
        for (int i = 0; i < coords.length; i++) {
            str += coords[i].toString() + " ";
        }
        return str;
    }
}
