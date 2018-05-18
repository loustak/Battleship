package sardois.lucas.Battleship;

import sardois.lucas.Battleship.AI.BegginerAI;

class Main {

    public static void main(String[] args) {
    	
    	/*
    	HumanPlayer p1 = new HumanPlayer("Player 1");
    	HumanPlayer p2 = new HumanPlayer("Player 2");
    	*/
    	BegginerAI p1 = new BegginerAI();
    	BegginerAI p2 = new BegginerAI();
    	
    	Game game = new Game(p1, p2);
    	game.play();
    }
}