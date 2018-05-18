package sardois.lucas.Battleship;

import java.util.Scanner;

import sardois.lucas.Battleship.AI.BeginnerAI;
import sardois.lucas.Battleship.AI.MediumAI;

class Main {

    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Battleship");
    	System.out.println("--Menu--");
    	System.out.println("1. Human VS Human");
    	System.out.println("2. Human VS Beginner");
    	System.out.println("3. Human VS Medium");
    	System.out.println("4. Human VS Advanced");
    	System.out.println("5. Quit");
    	
    	Player player1 = null;
    	Player player2 = null;
    	
    	System.out.print("Choose an option (a number): ");
    	int choice = Main.getNumber(input, 1, 5);
    	
    	switch (choice) {
    		case 1:
    			player1 = new HumanPlayer("Player 1");
    			player2 = new HumanPlayer("Player 2");
    			break;
    		case 2:
    			player1 = new HumanPlayer("Player 1");
    			player2 = new BeginnerAI();
    			break;
    		case 3:
    			player1 = new HumanPlayer("Player 1");
    			player2 = new MediumAI();
    			break;
    	}

    	GameUI game = new GameUI(player1, player2);
    	boolean playAgain = true;
    	
    	while (playAgain) {
    		game.play();
    		
    		System.out.println("Play again?");
    		System.out.println("1. Yes");
    		System.out.println("2. No");
    		System.out.print("Choose an option (a number): ");
    		
    		if (Main.getNumber(input, 1, 2) == 2) {
    			playAgain = false;
    		} else {
    			game = new GameUI(game.getLoser(), game.getWinner());
    		}
    	}
    	
    	System.out.println("Bye!");
    	input.close();
    }
    
    static int getNumber(Scanner input, int min, int max) {
    	int number;
    	boolean valid = false;
    	
    	do {
    		while (!input.hasNextInt()) {
    			input.next();
    			System.out.print("This is not a valid number. Please, enter again: ");
    		}
    		number = input.nextInt();
    		if (number < min || number > max) {
    			System.out.print("This is not a valid number. Please, enter again: ");
    		} else {
    			valid = true;
    		}
    	} while (!valid);
    	return number;
    }
}