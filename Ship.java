
public class Ship {

    private Coord start;
    private Coord end;
    private Coord[] coords;

    public Ship(String startCoord, String endCoord) throws java.lang.Exception {
        this.start = new Coord(startCoord);
        this.end = new Coord(endCoord);

        boolean isHorizontal = horizontal();
        int length = length(isHorizontal);

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

    private boolean horizontal() throws java.lang.Exception {
        if (start.getCoord2() == end.getCoord2() && start.getCoord1() != end.getCoord1()) {
            return true;
        } else if (start.getCoord1() == end.getCoord1() && start.getCoord2() != end.getCoord2()) {
            return false;
        } else {
            throw new Exception("The ship is neither horizontal nor vertical.");
        }
    }

    private int length(boolean isHorizontal) throws java.lang.Exception {
        if (isHorizontal) {
            return end.getCoord1() - start.getCoord1();
        } else {
            return end.getCoord2() - start.getCoord2();
        }
    }

    public Coord[] getCoords() {
        return coords;
    }

    public void shoot(Coord coord) {
        for (int i = 0; i < coords.length; i++) {
            if (coords[i].equals(coord)) {
                coords[i].setHit();
            }
        }
    }

    public boolean isHit(String missileCoord) {
        // Return true if the missile touched this ship, else false
        for (int i = 0; i < coords.length; i++) {
            if (coords[i].toString().equals(missileCoord)) {
                return true;
            }
        }
        return false;
    }

    public boolean hit(String missileCoord) {
        // Hit the ship at the designated coord
        for (int i = 0; i < coords.length; i++) {
            if (coords[i].toString().equals(missileCoord)) {
                coords[i].setHit();
            }
        }
        return isDestroyed();
    }

    public boolean isDestroyed() {
        // Return true if the ship has sink, else return false
        for (int i = 0; i < coords.length; i++) {
            if (!coords[i].isHit()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < coords.length; i++) {
            str += coords[i].toString() + " ";
        }
        return str;
    }
}
