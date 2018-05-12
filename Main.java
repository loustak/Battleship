
import java.util.Scanner;

class HumanVsHuman {

    public static void main(String[] args) {
        int[] shipSize = {/*5, 4, 3, 3, */2};
        Scanner input = new Scanner(System.in);
        Player player1;
        Player player2;

        System.out.println("Game start, the grid is " + Coord.getMinCoord() + " to " + Coord.getMaxCoord() + ".");
        // Ask both players for their names
        System.out.println("Player 1, enter your name: ");
        player1 = new Player(input.nextLine());
        System.out.println("Player 2, enter your name: ");
        player2 = new Player(input.nextLine());

        // Ask both player to place their fleet
        System.out.println(player1.getName() + " place your ships:");
        HumanVsHuman.placeFleet(player1, shipSize, input);
        System.out.println(player2.getName() + " place your ships:");
        HumanVsHuman.placeFleet(player2, shipSize, input);

        // Start the game
        HumanVsHuman.gameLoop(player1, player2, input);
    }

    static void gameLoop(Player player1, Player player2, Scanner input) {
        Player currentPlayer = player1;
        Player ennemy = player2;
        boolean gameIsFinished = false;

        while (gameIsFinished) {
            System.out.println(currentPlayer.getName() + " your turn:");
            System.out.println(currentPlayer);
            HumanVsHuman.shoot(ennemy, input);

            if (ennemy.lost()) {
                System.out.println(currentPlayer.getName() + " win !");
                gameIsFinished = true;
            }

            // Swap the roles
            if (currentPlayer == player1) {
                currentPlayer = player2;
                ennemy = player1;
            } else {
                currentPlayer = player1;
                ennemy = player2;
            }
        }
    }

    /**
     * Ask the player to place all the ships one by one
     * @param player is a Player object
     * @param shipSizes is and int array of the differents ships sizes to place
     */
    static void placeFleet(Player player, int[] shipSizes, Scanner input) {
        System.out.println(player);
        for (int size : shipSizes) {
            placeShip(player, size, input);
            System.out.println(player);
        }

        System.out.println("");
        System.out.println("");
    }

    /**
     * Ask the user to enter the coordinate of the ship until they are valid
     * @param player is a Player object
     * @param size is an int representing the size of the ship
     */
    static void placeShip(Player player, int size, Scanner input) {
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
                player.addShip(coordStart, coordEnd, size);
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
    static void shoot(Player ennemy, Scanner input) {
        String missileCoord;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter the coordinate to shoot at: ");
            missileCoord = input.nextLine();

            try {
                if (ennemy.shipHit(missileCoord) == null) {
                    System.out.println("Shoot missed");
                } else {
                    System.out.println("Ship touched!");
                }
                valid = true;
            } catch (CoordException exception) {
                System.out.println(exception.getMessage());
            } 
        }
    }
}
