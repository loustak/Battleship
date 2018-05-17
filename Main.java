
class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Player player[] = new Player[2];
        boolean gameIsFinished = false;
        int[] shipSizes = {/*5, 4, 3, 3, */2};

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~ Battleship ~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        // Ask both players for their names and for their ships placements 
        for (int i = 0; i < player.length; i++) {
            System.out.print("Player " + (i + 1) + ", enter your name: ");
            player[i] = new Player(input.nextLine());
            System.out.println(player[i].getName() + ", place your ships:");
            System.out.println(player[i].grid(true, false));
            // Ask to place each ship
            for (int size : shipSizes) {
                String coordStart;
                String coordEnd;
                boolean shipPlaced = false;

                System.out.println("Place a ship of size: " + size);

                while (!shipPlaced) {
                    System.out.print("Enter the start position of the ship: ");
                    coordStart = input.nextLine();

                    System.out.print("Enter the end position of the ship: ");
                    coordEnd = input.nextLine();

                    try {
                        player[i].addShip(coordStart, coordEnd, size);
                        shipPlaced = true;
                    } catch (CoordException e) {
                        System.out.println(e.getMessage());
                    } catch (ShipException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            System.out.println("Here is your fleet: ");
            System.out.println(player[i].grid(true, false));

            if (i != player.length - 1) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("~~~ Other Player Turn ~~~");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            }
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~ Game  start ~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        Player currentPlayer = player[0];
        Player ennemyPlayer = player[1];

        // Game loop
        while (!gameIsFinished) {
            System.out.println(currentPlayer.getName() + " your turn. Your shoot grid:");
            System.out.println(currentPlayer.grid(false, true));

            String missileCoord;
            boolean haveShoot = false;

            // Ask the player to shoot
            while (!haveShoot) {
                System.out.print("Enter the coordinate to shoot at: ");
                missileCoord = input.nextLine();

                try {
                    if (currentPlayer.shipHit(ennemyPlayer, missileCoord) == null) {
                        System.out.println("Shoot missed");
                    } else {
                        System.out.println("Ship touched");
                    }
                    haveShoot = true;
                } catch (CoordException exception) {
                    System.out.println(exception.getMessage());
                } 
            }

            // Lost condition
            if (ennemyPlayer.lost()) {
                System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(currentPlayer.getName() + " win !");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
                gameIsFinished = true;
            }

            // Swap the roles
            if (currentPlayer == player[0]) {
                currentPlayer = player[1];
                ennemyPlayer = player[0];
            } else {
                currentPlayer = player[0];
                ennemyPlayer = player[1];
            }

            if (!gameIsFinished) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("~~~ Other Player Turn ~~~");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            }
        }
    }
}