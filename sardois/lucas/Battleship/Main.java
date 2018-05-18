package sardois.lucas.Battleship;

import java.util.Scanner;

import sardois.lucas.Battleship.AI.BegginerAIPlayer;

class Main {

    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Battleship");
    	System.out.println("--Menu--");
    	System.out.println("1. Human VS Human");
    	System.out.println("2. Human VS Begginer");
    	System.out.println("3. Human VS Medium");
    	System.out.println("4. Human VS Advanced");
    	System.out.println("5. Quit");
    	
    	String optionString;
    	int optionNumber = 0;
    	Player player1 = null;
    	Player player2 = null;
    	
    	while (player1 == null && player2 == null) {
    		System.out.print("Choose an option (a number): ");
    		optionString = input.nextLine();
        	try {
        		optionNumber = Integer.parseInt(optionString);
        	} catch (Exception e) {
        		System.out.println("The option is not a valid number");
        		continue;
        	}
        	switch (optionNumber) {
        		case 1:
        			player1 = new HumanPlayer("Player 1");
        			player2 = new HumanPlayer("Player 2");
        			break;
        		case 2:
        			player1 = new HumanPlayer("Player 1");
        			player2 = new BegginerAIPlayer();
        			break;
        		default:
        			player1 = null;
        			player2 = null;
        			System.out.println("The number is invalid");
        			break;
        	}
    	}

    	
    	Game game = new Game(player1, player2);
    	Player winner = game.play();
    }
}