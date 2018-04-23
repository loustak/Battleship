import java.util.Scanner;
import java.util.ArrayList;

class Player {

    private int number;
    private int[] shipSize;
    private Map map;
	private ArrayList<Ship> fleet;

    public Player(int number, int[] shipSize) {
        this.number = number;
        this.shipSize = shipSize;
        map = new Map();
        fleet = new ArrayList<Ship>();
    }

    public Coord askCoord() {
        /* Retourne une coordonée valide */
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

    public void askFleet() {
        System.out.println("Player " + number + " turn.");        

        for (int i = 0; i < shipSize.length; i++) {
            System.out.println("\nEnter a position for a ship of size " + shipSize[i] + ".");
            askShipPosition(shipSize[i]);
        }

        System.out.println("Fleet is ready!");
    }

    public void askShipPosition(int shipSize) {
        Scanner input = new Scanner(System.in);
        boolean collide = false;
        String startCoord = null;
        String endCoord = null;
        Ship ship = null;

        while (ship == null) {
            System.out.print("Start coord: ");
            startCoord = input.nextLine();
            System.out.print("End coord: ");
            endCoord = input.nextLine();

            try {
                ship = new Ship(startCoord, endCoord);
                Ship inCollide = shipCollide(ship);
                if (inCollide != null) {
                    ship = null;
                    throw new Exception("The ship collide with another ship place at: " + inCollide);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        fleet.add(ship);
        map.addShip(ship);
        map.draw();
    }

    public Ship shipCollide(Ship shipToTest) {
        // Check if the given ship collide with any other sheep
        Coord[] coordsToCheck = shipToTest.getCoords();
        Ship shipToReturn = null;

        for (Ship ship : fleet) {
            shipToReturn = null;
            Coord[] coords = ship.getCoords();

            for (int j = 0; j < coords.length; j++) {
                for (int k = 0; k < coordsToCheck.length; k++) {
                    if (coords[j].equals(coordsToCheck[k])) {
                        return ship;
                    }
                }
            }
        }
        return null;
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
        Scanner input = new Scanner(System.in);
        String coordString = "";
        Coord coord = null;

        System.out.println("Player " + number + " turn.");
        System.out.println("Enter a position to shoot at: ");

        while (coord == null) {
            coordString = input.nextLine();
            try {
                coord = new Coord(coordString);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (Ship ship : fleet) {
            if (ship.isHit(coordString)) {
                if (ship.hit(coordString)) {
                    System.out.println("Sinked !");
                } else {
                    System.out.println("Touched !");
                }
            }
        }
    }
}