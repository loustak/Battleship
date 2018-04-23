
public class Map {

	private int width;
	private int height;
	private MapData[][] map;

	public Map() {
		width = Coord.getMaxCoord().getCoord1() - Coord.getMinCoord().getCoord1() + 1;
		height = Coord.getMaxCoord().getCoord2() - Coord.getMinCoord().getCoord2() + 1;

		map = new MapData[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j] = MapData.WATER;
			}
		}
	}

	public void addShip(Ship ship) {
		Coord[] coords = ship.getCoords();

	}

	public void draw() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				MapData data = map[i][j];
				if (data == MapData.WATER) {
					System.out.print("≈");
				} else if (data == MapData.SHIP) {
					System.out.print("S");
				} else if (data == MapData.FIRED) {
					System.out.print("x");
				} else if (data == MapData.SHIP_TOUCHED) {
					System.out.print("T");
				}
			}
			System.out.print("\n");
		}
	}
}