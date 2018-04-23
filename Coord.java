
public class Coord {

    private static String minCoordString = "A1";
    private static String maxCoordString = "J10";
    private static Coord minCoord = new Coord(minCoordString, true);
    private static Coord maxCoord = new Coord(maxCoordString, true);



    static public Coord getMinCoord() {
        return minCoord;
    }

    static public Coord getMaxCoord() {
        return maxCoord;
    }

    private int coord1;
    private int coord2;
    private boolean hit = false;

    public Coord(String coord) throws java.lang.Exception {
        int length = coord.length();
        if (length < getMinCoord().toString().length() || length > getMaxCoord().toString().length()) {
            throw new Exception(coord + ": length is invalid.");
        }

        try {
            extractCoords(coord);
        } catch (Exception e) {
            throw new Exception(coord + ": type is invalid.");
        }

        if (coord1 < getMinCoord().coord1 || coord2 < getMinCoord().coord2 ||
            coord1 > getMaxCoord().coord1 || coord2 > getMaxCoord().coord2) {
            throw new Exception(coord + ": out of the grid.");
        }
    }

    private Coord(String coord, boolean unsafe) {
        extractCoords(coord);
    }

    private void extractCoords(String coord) {
        coord1 = (int) coord.toUpperCase().charAt(0);
        String secondPart = coord.substring(1);
        coord2 = Integer.parseInt(secondPart); 
    }

    public Coord(int coord1, int coord2) {
        this.coord1 = coord1;
        this.coord2 = coord2;
    }

    public int getCoord1() {
        return coord1;
    }

    public int getCoord2() {
        return coord2;
    }

    public void setHit() {
        // fire on this coordinate
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Coord other = (Coord) obj;
        return getCoord1() == other.getCoord1() && getCoord2() == other.getCoord2();
    }

    public String toString() {
        return ((char)getCoord1()) + Integer.toString(getCoord2());
    }
}