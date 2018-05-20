
package sardois.lucas.Game;

/**
 * Defines all the rules of a game of battleship
 * @author Lucas
 *
 */
public final class GameRule {
	
	/**
	 * The top left coordinate of the grid
	 */
	public static final String minCoordString = "A1";
	/**
	 * The bottom right coordinate of the grid
	 */
    public static final String maxCoordString = "J10";
    
    /**
     * All the ships size the user will need to place on it's grid
     */
    public static final int[] shipSizes = {5, 4, 3, 3, 2};
}
