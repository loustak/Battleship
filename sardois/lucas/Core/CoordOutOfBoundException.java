package sardois.lucas.Core;

class CoordOutOfBoundException extends RuntimeException {

    public CoordOutOfBoundException() {
        super("The coordinate is out of the grid.");
    }
}