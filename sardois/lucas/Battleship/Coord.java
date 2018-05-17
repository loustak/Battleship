
package sardois.lucas.Battleship;

import sardois.lucas.Battleship.Util.Random;

public class Coord {

    private static String minCoordString = "A1";
    private static String maxCoordString = "J10";
    private static Coord minCoord = new Coord(getHorizontalPartFromString(minCoordString), getVerticalPartFromString(minCoordString), true);
    private static Coord maxCoord = new Coord(getHorizontalPartFromString(maxCoordString), getVerticalPartFromString(maxCoordString), true);

    public static Coord getMinCoord() {
        return minCoord;
    }

    public static Coord getMaxCoord() {
        return maxCoord;
    }

    public static boolean isStringLengthValid(String coord) {
        int length = coord.length();
        if (length < minCoordString.length() || length > maxCoordString.length()) {
            return false;
        }
        return true;
    }

    public static int getHorizontalPartFromString(String coord) {
        try {
            return (int) coord.toUpperCase().charAt(0);    
        } catch (Exception exception) {
            return -1;
        }
    }

    public static int getVerticalPartFromString(String coord) {
        try {
            String verticalPart = coord.substring(1);
            return Integer.parseInt(verticalPart);
        } catch (Exception exception) {
            return -1;
        }
    }

    public static boolean isInRange(int horizontal, int vertical) {
        if (horizontal < getMinCoord().getCoordHorizontal() || vertical < getMinCoord().getCoordVertical() ||
            horizontal > getMaxCoord().getCoordHorizontal() || vertical > getMaxCoord().getCoordVertical()) {
            return false;
        }
        return true;
    }
    
    public static Coord getRandomCoord(int margin) {
    	int minHorizontal = getMinCoord().getCoordHorizontal();
    	int maxHorizontal = getMaxCoord().getCoordHorizontal();
    	int minVertical = getMinCoord().getCoordVertical();
    	int maxVertical = getMaxCoord().getCoordVertical();
    	int horizontal = Random.randomRange(minHorizontal, maxHorizontal - margin);
    	int vertical = Random.randomRange(minVertical, maxVertical - margin);
    	return new Coord(horizontal, vertical);
    }

    private int coordHorizontal;
    private int coordVertical;
    private boolean hit = false;

    public Coord(int coordHorizontal, int coordVertical) {
        if (!Coord.isInRange(coordHorizontal, coordVertical)) {
            throw new CoordOutOfBoundException();
        }

        this.coordHorizontal = coordHorizontal;
        this.coordVertical = coordVertical;
    }
    
    private Coord(int coordHorizontal, int coordVertical, boolean unsafe) {
    	this.coordHorizontal = coordHorizontal;
        this.coordVertical = coordVertical;
    }

    public int getCoordHorizontal() {
        return coordHorizontal;
    }

    public int getCoordVertical() {
        return coordVertical;
    }
    
    public void setHit() {
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
        return getCoordHorizontal() == other.getCoordHorizontal() && getCoordVertical() == other.getCoordVertical();
    }

    public String toString() {
        return ((char)getCoordHorizontal()) + Integer.toString(getCoordVertical());
    }
}
