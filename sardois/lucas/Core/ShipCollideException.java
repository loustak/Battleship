package sardois.lucas.Core;

/**
 * Exception thrown when a ship collide with another ship of the player's fleet
 * @author Lucas
 *
 */
public class ShipCollideException extends RuntimeException {

	/**
	 * Create the exception
	 */
	public ShipCollideException() {
		super("The ship collide with another ship of the fleet.");
	}
}
