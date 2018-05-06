
/**
 * Represent a player grid
 */
public class Grid {

	private int width;
	private int height;
	private GridData[][] grid;

	/**
	 * Construct an empty grid
	 */
	public Grid() {
		width = Coord.getMaxCoord().getCoord1() - Coord.getMinCoord().getCoord1() + 1;
		height = Coord.getMaxCoord().getCoord2() - Coord.getMinCoord().getCoord2() + 1;

		empty();
	}

	/**
	 * Empty the grid filling it with water
	 */
	public void empty() {
		grid = new GridData[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = GridData.WATER;
			}
		}
	}

	/**
	 * Add the Ship symbol to the grid
	 * @param ship is a Ship object
	 */
	public void addShip(Ship ship) {
		Coord[] coords = ship.getCoords();
		for (Coord coord : coords) {
			int x = coord.getCoord1() - Coord.getMinCoord().getCoord1();
			int y = coord.getCoord2() - Coord.getMinCoord().getCoord2();
			grid[y][x] = GridData.SHIP;
		}
	}

	/**
	 * Add the correct fire symbol to the grid
	 * @param missileCoord is a Coord object representing the position fired at
	 * @param touched is a boolean indicating wether the shoot touched a ship or not
	 */
	public void addShoot(Coord missileCoord, boolean touched) {
		int x = missileCoord.getCoord1() - Coord.getMinCoord().getCoord1();
		int y = missileCoord.getCoord2() - Coord.getMinCoord().getCoord2();
		if (touched) {
			grid[y][x] = GridData.SHIP_TOUCHED;
		} else {
			grid[y][x] = GridData.FIRED;
		}
	}

	/**
	 * Draw the grid
	 */
	public void draw() {
		Coord minCoord = Coord.getMinCoord();
		int minLetter = minCoord.getCoord1();
		int minNumber = minCoord.getCoord2();
		int maxCoordStringLength = String.valueOf(Coord.getMaxCoord().getCoord2()).length();

		String numberString;
		int offset = 0;

		// Draw the letter coords line
		drawSpace(maxCoordStringLength + 1);
		for (int i = 0; i < width; i++) {
			System.out.print(String.valueOf((char)(minLetter + i)));
			System.out.print(" ");
		}
		System.out.println("");

		// Draw the rest of the grid
		for (int i = 0; i < height; i++) {
			numberString = Integer.toString(minNumber + i);
			System.out.print(numberString);
			drawSpace(maxCoordStringLength - numberString.length() + 1);

			for (int j = 0; j < width; j++) {
				GridData data = grid[i][j];

				if (data == GridData.WATER) {
					System.out.print(".");
				} else if (data == GridData.SHIP) {
					System.out.print("o");
				} else if (data == GridData.FIRED) {
					System.out.print("*");
				} else if (data == GridData.SHIP_TOUCHED) {
					System.out.print("x");
				}
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	/**
	 * @param n is an int corresponding to the number of "space" character to draw
	 */
	public void drawSpace(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
	}
}