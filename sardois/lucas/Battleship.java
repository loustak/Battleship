package sardois.lucas;

import java.util.Scanner;

import sardois.lucas.Player.AI.BeginnerAI;
import sardois.lucas.Player.AI.HardAI;
import sardois.lucas.Player.AI.MediumAI;
import sardois.lucas.Game.GameUI;
import sardois.lucas.Player.HumanPlayer;
import sardois.lucas.Player.Player;

/**
 * 
 * @author Lucas
 * Main class of this package. It will promp to the user multiple 
 * games choices and then start a game.
 */
public class Battleship {

	/**
	 * Main method
	 * @param args the arguments to pass on the program execution.
	 */
    public static void main(String[] args) {
    	Scanner inputNumber = new Scanner(System.in);
    	Scanner inputName = new Scanner(System.in);
    	Player player1 = null;
    	Player player2 = null;
    	
    	System.out.println("Battleship");
    	System.out.println("--Menu--");
    	System.out.println("1. Human VS Human");
    	System.out.println("2. Human VS Beginner");
    	System.out.println("3. Human VS Medium");
    	System.out.println("4. Human VS Advanced");
    	System.out.println("5. Quit");
    	
    	System.out.print("Choose an option (a number): ");
    	int choice = Battleship.getNumber(inputNumber, 1, 5);
    	
    	switch (choice) {
    		case 1:
    			System.out.print("Player 1 enter your name: ");
    			player1 = new HumanPlayer(inputName.nextLine());
    			System.out.print("Player 2 enter your name: ");
    			player2 = new HumanPlayer(inputName.nextLine());
    			break;
    		case 2:
    			System.out.print("Enter your name: ");
    			player1 = new HumanPlayer(inputName.nextLine());
    			player2 = new BeginnerAI();
    			break;
    		case 3:
    			System.out.print("Enter your name: ");
    			player1 = new HumanPlayer(inputName.nextLine());
    			player2 = new MediumAI();
    			break;
    		case 4:
    			System.out.print("Enter your name: ");
    			player1 = new HumanPlayer(inputName.nextLine());
    			player2 = new HardAI();
    			break;
    		case 5:
    			break;
    	}

    	if (choice != 5) {
    		GameUI game = new GameUI(player1, player2);
        	boolean playAgain = true;
        	
        	while (playAgain) {
        		game.play();
        		
        		System.out.println("Play again?");
        		System.out.println("1. Yes");
        		System.out.println("2. No");
        		System.out.print("Choose an option (a number): ");
        		
        		if (Battleship.getNumber(inputNumber, 1, 2) == 2) {
        			playAgain = false;
        		} else {
        			game.reset();
        		}
        	}
    	}
    	
    	System.out.println("Bye!");
    	inputNumber.close();
    	inputName.close();
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