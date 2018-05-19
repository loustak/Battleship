package sardois.lucas.Battleship;

import java.util.Scanner;

class HumanPlayer extends Player {

	Scanner input;

	public HumanPlayer(String name) {
		super(name);
		// Ask for the player name
		input = new Scanner(System.in);
		System.out.print(getName() + " enter your name: ");
		this.name = input.nextLine();
	}
	
	public final boolean hasUI() {
		return true;
	}
	
	@Override
	public void placeFleet(int shipSizes[]) {
		Ship ship;
		
		for (int size : shipSizes) {
			ship = placeShip(size);
			if (collide(ship)) {
				throw new ShipCollideException();
			}
			fleet.add(ship);
			System.out.println(grid(true, false));
		}
	}

	@Override
    public Ship placeShip(int shipSize) {
    	System.out.println("Place a ship of size " + shipSize);
    	Coord startCoord, endCoord;
        Ship shipToPlace = null;
        
        while (shipToPlace == null) {
            System.out.print("Enter the start coordinate: ");
            startCoord = askCoord();
            System.out.print("Enter the end coordinate: ");
            endCoord = askCoord();
            
            int orientation = Ship.getOrientation(startCoord, endCoord);
            if (orientation == -1) {
                System.out.print("The ship must be placed either vertically or horizontally. Please, place the ship again.");
            } else {
            	boolean isHorizontal = orientation == 0;
                int length = Ship.length(startCoord, endCoord, isHorizontal);
                if (Math.abs(length) != shipSize) {
                	System.out.print("The ship size must be " + shipSize + " instead of " + length + ". Please, place the ship again.");
                } else {
                	shipToPlace = new Ship(startCoord, isHorizontal, length);
                    // If the ship is coliding with any other ship we destroy it
                    if (collide(shipToPlace)) {
                    	System.out.print("This ship collide with another ship of your fleet. Please, place the ship again.");
                    	shipToPlace = null;
                    }
                }
            }
        }
        
        return shipToPlace;
    }
    
    public Shoot shoot(Player ennemyPlayer) {
    	return shootAt(ennemyPlayer, askCoord());
    }
    
    private Coord askCoord() {
        String stringCoord;
        Coord coordReturn = null;

        while (coordReturn == null) {
            stringCoord = input.nextLine();
            // Check the length
            if (!Coord.isStringLengthValid(stringCoord)) {
                System.out.println("Length is invalid.");
            } else  {
                // Try to get int values from the string
                int horizontal = Coord.getHorizontalPartFromString(stringCoord);
                int vertical = Coord.getVerticalPartFromString(stringCoord);
                
                if (horizontal < 0 || vertical < 0) {
                    System.out.println("The coordinate does not follow the correct format.");
                } else if (!Coord.isInRange(horizontal, vertical)) {
                    System.out.println("The coordinate is not inside the grid which if from " + Coord.getMinCoord() + " to " + Coord.getMaxCoord() + ".");
                } else  {
                    // At this point the coordinate is valid
                    coordReturn = new Coord(horizontal, vertical);
                }
            }
            if (coordReturn == null) {
            	System.out.print("Enter the coordinate again: ");
            }
        }
        return coordReturn;
    }

    private String addSpace(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

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
                    Shoot shoot = firedAt(currentCoord);
                    if (shoot != null) {
                    	ShootState state = shoot.getShootState();
                        if (state == ShootState.TOUCHED || state == ShootState.SINK) {
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