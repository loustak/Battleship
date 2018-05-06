
public class CoordExceptionInvalidRange extends CoordException {

    public CoordExceptionInvalidRange(String coord) {
        super(coord, "out of the grid");
    }
}