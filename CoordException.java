
public class CoordException extends Exception {

    public CoordException(String coord, String message) {
        super(coord + ": " + message);
    }
}