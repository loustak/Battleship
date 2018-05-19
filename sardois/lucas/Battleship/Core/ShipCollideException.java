package sardois.lucas.Battleship.Core;

public class ShipCollideException extends RuntimeException {

	public ShipCollideException() {
		super("The ship collide with another ship of the fleet.");
	}
}
