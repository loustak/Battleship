package sardois.lucas.Battleship;

import sardois.lucas.Battleship.AI.BegginerAI;

class Main {

    public static void main(String[] args) {
    	
    	HumanPlayer p1 = new HumanPlayer("Player 1");
    	BegginerAI p2 = new BegginerAI();
    	
    	Game game = new Game(p1, p2);
    	System.out.println(game.play().getName() + " win the game !");
    }
}