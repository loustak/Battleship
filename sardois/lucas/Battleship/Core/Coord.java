
package sardois.lucas.Battleship.Core;

import sardois.lucas.Battleship.Game.GameRule;
import sardois.lucas.Util.Util;

public class Coord {

    private static final Coord minCoord = new Coord(getHorizontalPartFromString(GameRule.minCoordString), getVerticalPartFromString(GameRule.minCoordString), true);
    private static final Coord maxCoord = new Coord(getHorizontalPartFromString(GameRule.maxCoordString), getVerticalPartFromString(GameRule.maxCoordString), true);

    public static Coord getMinCoord() {
        return minCoord;
    }

    public static Coord getMaxCoord() {
        return maxCoord;
    }

    public static boolean isStringLengthValid(String coord) {
        int length = coord.length();
        if (length < GameRule.minCoordString.length() || length > GameRule.maxCoordString.length()) {
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
    	int horizontal = Util.randomRange(minHorizontal, maxHorizontal - margin);
    	int vertical = Util.randomRange(minVertical, maxVertical - margin);
    	return new Coord(horizontal, vertical);
    }

    protected int horizontal;
    protected int vertical;

    public Coord(int coordHorizontal, int coordVertical) {
        if (!Coord.isInRange(coordHorizontal, coordVertical)) {
            throw new CoordOutOfBoundException();
        }

        this.horizontal = coordHorizontal;
        this.vertical = coordVertical;
    }
    
    public Coord(Coord coord) {
    	horizontal = coord.getCoordHorizontal();
    	vertical = coord.getCoordVertical();
    }
    
    private Coord(int coordHorizontal, int coordVertical, boolean unsafe) {
    	this.horizontal = coordHorizontal;
        this.vertical = coordVertical;
    }

    public int getCoordHorizontal() {
        return horizontal;
    }

    public int getCoordVertical() {
        return vertical;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coord)) return false;
        Coord other = (Coord) obj;
        return getCoordHorizontal() == other.getCoordHorizontal() && getCoordVertical() == other.getCoordVertical();
    }

    public String toString() {
        return ((char)getCoordHorizontal()) + Integer.toString(getCoordVertical());
    }
}
