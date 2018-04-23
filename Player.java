import java.util.Scanner;
import java.util.ArrayList;

class Player {

    private int number;
	ArrayList<Ship> fleet;

    public Player(int number) {
        this.number = number;
        fleet = new ArrayList<Ship>();
    }

    public boolean lost() {
        for (Ship ship : fleet) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public void fire(Player player) {
        System.out.println("Player " + number + " turn.");
        System.out.println("Enter a position to shoot at: ");
        String missileCoord = askCoord().toString();
        for (Ship ship : fleet) {
            if (ship.isHit(missileCoord)) {
                if (ship.hit(missileCoord)) {
                    System.out.println("Sinked !");
                } else {
                    System.out.println("Touched !");
                }
            }
        }
    }

    public void askFleet() {
        System.out.println("Player " + number + " turn.");
        System.out.println("Enter a position for the Carrier (size 5).");
        askShipPosition(5);
        
        System.out.println("\nEnter a position for the Battleship (size 4).");
        askShipPosition(4);

        System.out.println("\nEnter a position for the Cruiser (size 3).");
        askShipPosition(3);

        System.out.println("\nEnter a position for the Submarine (size 3).");
        askShipPosition(3);

        System.out.println("\nEnter a position for the Destroyer (size 2).");
        askShipPosition(2);

        System.out.println("Fleet placed!");
    }

    public void askShipPosition(int shipSize) {
        boolean isValid = false;
        boolean collide = false;
        Coord startCoord = null;
        Coord endCoord = null;
        Ship ship = null;

        while (ship == null) {
            System.out.print("Start coord: ");
            startCoord = askCoord();
            System.out.print("End coord: ");
            endCoord = askCoord();

            try {
                ship = new Ship(startCoord, endCoord);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return ship;
    }

    public Coord askCoord() {
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        String coordString;
        Coord coord = null;

        while (coord == null) {
            coordString = input.nextLine();
            try {
                coord = new Coord(coordString);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return coord;
    }

    public boolean shipCollide(Ship shipToTest) {
        // Check if the given ship collide with any other sheep
        Coord[] coordsToCheck = shipToTest.getCoords();

        for (Ship ship : fleet) {
            Coord[] coords = ship.getCoords();

            for (int j = 0; j < coords.length; j++) {
                for (int k = 0; k < coordsToCheck.length; k++) {
                    if (coords[j].equals(coordsToCheck[k])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}