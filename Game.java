
public class Game {

    private int[] shipSize = {5, 4, 3, 3, 2};
	private Player player1;
	private Player player2;

    public Game() {
        System.out.println("Game start, the grid is " + Coord.getMinCoord() + " to " + Coord.getMaxCoord() + ".");
        player1 = new Player(1, shipSize);
        player2 = new Player(2, shipSize);

        player1.askFleet();
        player2.askFleet();
    }

    /*
    public void loop() {
        while (true) {
            player1.fire(player2);
            if (player2.lost()) {
                System.out.println("Player 1 win !");
                break;
            }
            player2.fire(player1);
            if (player1.lost()) {
                System.out.println("Player 2 win !");
                break;
            }
        }
    }
    */
}