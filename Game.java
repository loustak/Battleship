
public class Game {

    private int[] shipSize = {/*5, 4, 3, 3, */2};
	private Player player1;
	private Player player2;

    public Game() {
        System.out.println("Game start, the grid is " + Coord.getMinCoord() + " to " + Coord.getMaxCoord() + ".");
        player1 = new HumanPlayer();
        player2 = new HumanPlayer();

        System.out.println();

        System.out.println(player1.getName() + " place your ships:");
        player1.placeFleet(shipSize);
        System.out.println(player2.getName() + " place your ships:");
        player2.placeFleet(shipSize);
    }

    public void loop() {
        while (true) {
            /* Player 1 part */
            System.out.println(player1.getName() + " your turn:");
            System.out.println(player1);
            player1.shoot(player2);

            if (player2.lost()) {
                System.out.println(player1.getName() + " win !");
                break;
            }

            /* Player 2 part */
            System.out.println("============================");
            System.out.println(player2.getName() + " your turn:");
            System.out.println(player2);
            player2.shoot(player1);

            if (player1.lost()) {
                System.out.println(player2.getName() + " win !");
                break;
            }
            System.out.println("============================");
        }
    }
}