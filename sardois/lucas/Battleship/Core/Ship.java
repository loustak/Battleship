package sardois.lucas.Battleship.Core;

/**
 * A ship is an array of coordinates
 */
public class Ship {

    // Coord array representing the positions of the ship
    private CoordShoot[] coords;

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

    public static int getOrientation(Coord start, Coord end) {
        if (start.getCoordHorizontal() != end.getCoordHorizontal() && start.getCoordVertical() == end.getCoordVertical()) {
            return 0;
        } else if (start.getCoordVertical() != end.getCoordVertical() && start.getCoordHorizontal() == end.getCoordHorizontal()) {
            return 1;
        }
        return -1;
    }

    public static int length(Coord start, Coord end, boolean isHorizontal) {
    	int length;
        if (isHorizontal) {
            length = end.getCoordHorizontal() - start.getCoordHorizontal();
        } else {
            length = end.getCoordVertical() - start.getCoordVertical();
        }
        return length + (int)Math.signum(length);
    }

    public int length() {
        return getCoordArray().length;
    }

    public Coord[] getCoordArray() {
        return coords;
    }

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

    public boolean isDestroyed() {
        for (int i = 0; i < coords.length; i++) {
            if (coords[i].getShootState() == ShootState.NOT_SHOOT) {
                return false;
            }
        }
        return true;
    }

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
