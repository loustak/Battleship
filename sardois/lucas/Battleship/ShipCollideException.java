package sardois.lucas.Battleship;

public class ShipCollideException extends RuntimeException {

	public ShipCollideException() {
		super("The ship collide with another ship of the fleet.");
	}
}
