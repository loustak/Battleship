
import java.util.Scanner;

/* 
* A HumanPlayer IS a Player. 
*/
class HumanPlayer extends Player {

	Scanner input;

	public HumanPlayer(String name) {
		super("Anon")
		// Ask for the player name
		input = new Scanner(System.in);
		System.out.print("Player " + (i + 1) + ", enter your name: ");
		name = input.nextLine();
	}


    public Ship addShip(int shipSize) {
        Coord start = askForCoord();
        Coord end = askForCoord();

        if (Math.abs(Coord.length(start, end)) == 0) {
            System.out.println("The ship length is not equals to " + shipSize + ".");
        } else {
            Ship ship = new Ship();
        }
    }

    private Coord askForCoord() {
        String stringCoord;
        Coord coordReturn = null;
        boolean valid = false;

        while (!valid) {
            stringCoord = input.nextLine();
            // Check the length
            if (!Coord.isLengthValid(stringCoord)) {
                System.out.println("Length is invalid.");
            } else  {
                // Try to get int values from the string
                int horizontal = Coord.getHorizontalPartFromString(stringCoord);
                int vertical = Coord.getVerticalPartFromString(stringCoord);
                if (horizontal < 0 || vertical < 0) {
                    System.out.println("The coordinate does not follow the correct format.");
                } else if (!Coord.isInRage(horizontal, vertical)) {
                    System.out.println("The coordinate is not inside the grid which if from " + Coord.getMinCoord() + " to " + Coord.getMaxCoord() ".");
                } else  {
                    // The coordinate is valid
                    coordReturn = new Coord(horizontal, vertical);
                    valid = true;
                }
            }
        }
        return coordReturn;
    }

    /**
     * @param n is an int corresponding to the number of "space" character to draw
     * @return a String composed of n spaces
     */
    private String addSpace(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * @param  displayShips  is a boolean. If set to true the grid will contains all ships positions
     * @param  displayShoots is a boolean. If set to true the grid will contains all shoots positions
     * @return a String representing the player's grid
     */
    public String grid(boolean displayShips, boolean displayShoots) {
        Coord minCoord = Coord.getMinCoord();
        Coord maxCoord = Coord.getMaxCoord();
        // Will be used to move inside the grid
        Coord currentCoord;

        // Compute the width & height of the grid
        int width = maxCoord.getCoordHorizontal() - minCoord.getCoordHorizontal() + 1;
        int height = maxCoord.getCoordVertical() - minCoord.getCoordVertical() + 1;
        // Get the starting letter of the grid
        int minLetterValue = minCoord.getCoordHorizontal();
        // Get the starting number of the grid
        int minNumber = minCoord.getCoordVertical();
        // Compute the maximum length of a coordinate as a string, 
        // to properly align the grid
        int maxCoordStringLength = String.valueOf(maxCoord.getCoordVertical()).length();

        String numberString;
        StringBuilder stringBuilder = new StringBuilder();

        // Draw the letter coords line
        stringBuilder.append(addSpace(maxCoordStringLength + 1));
        for (int i = 0; i < width; i++) {
            stringBuilder.append(String.valueOf((char)(minLetterValue + i)));
            stringBuilder.append(" ");
        }
        stringBuilder.append("\n");

        // Draw the rest of the grid
        for (int i = 0; i < height; i++) {
            numberString = Integer.toString(minNumber + i);
            // Add the current number
            stringBuilder.append(numberString);
            // Add enough spaces to align correctly the grid
            stringBuilder.append(addSpace(maxCoordStringLength - numberString.length() + 1));

            for (int j = 0; j < width; j++) {
                // Get the current coordinate
                currentCoord = new Coord((char)(minLetterValue + j), i + 1);

                if (displayShoots) {
                    Coord fired = firedAt(currentCoord);
                    if (fired != null) {
                        if (fired.isHit()) {
                            stringBuilder.append("x");
                        } else {
                            stringBuilder.append("*");
                        }
                    } else if (!displayShips) {
                        stringBuilder.append(".");
                    }
                }
                if (displayShips) {
                    if (isShipAt(currentCoord)) {
                        stringBuilder.append("o");
                    } else {
                        stringBuilder.append(".");
                    }
                }

                if (!displayShips && !displayShoots) {
                    stringBuilder.append(".");
                }

                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}