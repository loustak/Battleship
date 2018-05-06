
import java.util.Scanner;

/**
 * Class representing a Human Player able to interact with a Text-based User Interface (TUI)
 */
class HumanPlayer extends Player {

    private Scanner input;

    public HumanPlayer() {
        input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = input.nextLine();
    }

    /**
     * Ask the player to place all the ships one by one
     * @param shipSizes are the differents ships sizes to place
     */
    @Override
    public void placeFleet(int[] shipSizes) {
        drawGrid();
        for (int size : shipSizes) {
            placeShipTUI(size);
            drawGrid();
        }
        grid.empty();

        System.out.println("");
        System.out.println("");
    }

    /**
     * Ask the user to enter the coordinate of the ship until they are valid
     * @param an int representing the size of the ship
     */
    private void placeShipTUI(int size) {
        String coordStart;
        String coordEnd;
        boolean valid = false;

        System.out.println("Place a ship of size: " + size);
        while (!valid) {
            System.out.print("Enter the start position of the ship: ");
            coordStart = input.nextLine();
            System.out.print("Enter the end position of the ship: ");
            coordEnd = input.nextLine();

            try {
                addShip(coordStart, coordEnd, size);
                valid = true;
            } catch (CoordException e) {
                System.out.println(e.getMessage());
            } catch (ShipException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Ask the player the coordinate to shoot at
     * @return a boolean set to true if a ship was touched, else return false
     */
    @Override
    public void shoot(Player ennemy) {
        String fireCoord;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter the coordinate to shoot at: ");
            fireCoord = input.nextLine();
            try {
                if (shipHit(ennemy, fireCoord) == null) {
                    System.out.println("Shoot missed");
                } else {
                    System.out.println("Ship touched!");
                }
                valid = true;
            } catch (CoordException e) {
                System.out.println(e.getMessage());
            } 
        }
    }
}