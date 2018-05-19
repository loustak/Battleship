package sardois.lucas.Battleship.Core;


class CoordOutOfBoundException extends RuntimeException {

    public CoordOutOfBoundException() {
        super("The coordinate is out of the grid.");
    }
}