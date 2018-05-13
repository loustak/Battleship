
/**
 * An utility coordinate class. A coordinate is composed of two int part.
 */
public class Coord {

    private static String minCoordString = "A1";
    private static String maxCoordString = "J10";
    private static Coord minCoord = new Coord(getCoordHorizontalFromString(minCoordString), getCoordVerticalFromString(minCoordString));
    private static Coord maxCoord = new Coord(getCoordHorizontalFromString(maxCoordString), getCoordVerticalFromString(maxCoordString));

    /**
     * @return The first coordinate of the game as a Coord object
     */
    static public Coord getMinCoord() {
        return minCoord;
    }

    /**
     * @return The last coordinate of the game as a Coord object
     */
    static public Coord getMaxCoord() {
        return maxCoord;
    }

    private int coordHorizontal;
    private int coordVertical;
    private boolean hit = false;

    /**
     * @param coord The string coordinate to convert to the object
     * @throws CoordException Coordinate exception describing the error
     */
    public Coord(String coord) throws CoordException {
        int length = coord.length();
        // Check that the coord length is valid
        if (length < minCoordString.length() || length > maxCoordString.length()) {
            throw new CoordExceptionInvalidLength(coord);
        }

        try {
            // Try to get the two components of the coordinate
            coordHorizontal = getCoordHorizontalFromString(coord);
            coordVertical = getCoordVerticalFromString(coord);
        } catch (Exception e) {
            throw new CoordExceptionInvalidType(coord);
        }

        // Check if the coordinate is in the grid define by the max and min coord
        if (coordHorizontal < getMinCoord().coordHorizontal || coordVertical < getMinCoord().coordVertical ||
            coordHorizontal > getMaxCoord().coordHorizontal || coordVertical > getMaxCoord().coordVertical) {
            throw new CoordExceptionInvalidRange(coord);
        }
    }

    /**
     * This constructor doesn't do any check
     * @param coordHorizontal The horizontal part of coordinate as an int
     * @param coordVertical The vertical part of the coordinate as an int
     */
    public Coord(int coordHorizontal, int coordVertical) {
        this.coordHorizontal = coordHorizontal;
        this.coordVertical = coordVertical;
    }

    /**
     * Get the horizontal part of a String coordinate as an int
     * @param coord The coordinate to decompose
     */
    private static int getCoordHorizontalFromString(String coord) {
        return (int) coord.toUpperCase().charAt(0);
    }

    /**
     * Get the vertical part of a String coordinate as an int
     * @param coord The coordinate to decompose
     */
    private static int getCoordVerticalFromString(String coord) {
        String verticalPart = coord.substring(1);
        return Integer.parseInt(verticalPart); 
    }

    /**
     * @return The horizontal part of the coordinate as an int
     */
    public int getCoordHorizontal() {
        return coordHorizontal;
    }

    /**
     * @return The vertical part of the coordinate as an int
     */
    public int getCoordVertical() {
        return coordVertical;
    }

    /**
     * Set the hit flag of the coordinate. Used to know where player fired
     */
    public void setHit() {
        hit = true;
    }

    /**
     * @return a boolean set to true if this coordinate was hit, else it returns fals
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * @param obj The object to test the equality with
     * @return a boolean indicating if the two objects are equals or not
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Coord other = (Coord) obj;
        return getCoordHorizontal() == other.getCoordHorizontal() && getCoordVertical() == other.getCoordVertical();
    }

    /**
     * @return the coordinate as a String
     */
    public String toString() {
        return ((char)getCoordHorizontal()) + Integer.toString(getCoordVertical());
    }
}