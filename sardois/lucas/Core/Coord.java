package sardois.lucas.Core;

import sardois.lucas.Game.GameRule;
import sardois.lucas.Util.Util;

/**
 * Used to modelise a coordinate on the game's grid.
 * A coordinate is composed of two parts, the horizontal and vertical part.
 * @author Lucas
 *
 */
public class Coord {

    private static final Coord minCoord = new Coord(getHorizontalPartFromString(GameRule.minCoordString), getVerticalPartFromString(GameRule.minCoordString), true);
    private static final Coord maxCoord = new Coord(getHorizontalPartFromString(GameRule.maxCoordString), getVerticalPartFromString(GameRule.maxCoordString), true);

    /**
     * 
     * @return the top left coordinate of the grid
     */
    public static Coord getMinCoord() {
        return minCoord;
    }

    /**
     * 
     * @return the bottom right coordinate of the grid
     */
    public static Coord getMaxCoord() {
        return maxCoord;
    }

    /**
     * Check where a string have a valid length or not
     * @param coord the coordinate to check
     * @return true if the string have a valid length, else false
     */
    public static boolean isStringLengthValid(String coord) {
        int length = coord.length();
        if (length < GameRule.minCoordString.length() || length > GameRule.maxCoordString.length()) {
            return false;
        }
        return true;
    }

    /**
     * Get the horizontal part from a string
     * @param coord the coordinate to check
     * @return the horizontal part if possible, else return -1
     */
    public static int getHorizontalPartFromString(String coord) {
        try {
            return (int) coord.toUpperCase().charAt(0);    
        } catch (Exception exception) {
            return -1;
        }
    }

    /**
     * Get the vertical part from a string
     * @param coord the coordinate to check
     * @return the vertical part if possible, else return -1
     */
    public static int getVerticalPartFromString(String coord) {
        try {
            String verticalPart = coord.substring(1);
            return Integer.parseInt(verticalPart);
        } catch (Exception exception) {
            return -1;
        }
    }

    /**
     * Check wheter the two int are in the grid or not
     * @param horizontal the horizontal position to check for
     * @param vertical the vertical position to check for
     * @return true if both horizontal and vertical position are inside the grid, else return false
     */
    public static boolean isInRange(int horizontal, int vertical) {
        if (horizontal < getMinCoord().getCoordHorizontal() || vertical < getMinCoord().getCoordVertical() ||
            horizontal > getMaxCoord().getCoordHorizontal() || vertical > getMaxCoord().getCoordVertical()) {
            return false;
        }
        return true;
    }
    
    /**
     * Used to get a random coordinate inside a grid
     * @param margin the margin of the grid
     * @return a random coordinate inside the game's grid
     */
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

    /**
     * Construct a new coordinate. It will throw an checked exception if the
     * coordinate is outside the game's grid
     * @param horizontal the horizontal value
     * @param vertical the vertical value
     * 
     */
    public Coord(int horizontal, int vertical) {
        if (!Coord.isInRange(horizontal, vertical)) {
            throw new CoordOutOfBoundException();
        }

        this.horizontal = horizontal;
        this.vertical = vertical;
    }
    
    /**
     * Copy a coordinate
     * @param coord the coordinate to make a copy
     */
    public Coord(Coord coord) {
    	horizontal = coord.getCoordHorizontal();
    	vertical = coord.getCoordVertical();
    }
    
    /**
     * Create a coordinate whitout check
     * @param horizontal the horizontal value
     * @param vertical the vertical value
     * @param unsafe used to make this function declaration different from the previous one
     */
    private Coord(int horizontal, int vertical, boolean unsafe) {
    	this.horizontal = horizontal;
        this.vertical = vertical;
    }

    /**
     * 
     * @return the horizontal part of the coordinate
     */
    public int getCoordHorizontal() {
        return horizontal;
    }

    /**
     * 
     * @return the vertical part of the coordinate
     */
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
