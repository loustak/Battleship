
public class ShipExceptionSize extends ShipException {

    public ShipExceptionSize(int size) {
        super("the ship size must be " + size);
    }
}