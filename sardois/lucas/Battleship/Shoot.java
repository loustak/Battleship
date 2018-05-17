package sardois.lucas.Battleship;

public class Shoot {
	
	private Coord coord;
	private ShootState shootState;
	
	public Shoot(Coord coord, ShootState shootState) {
		this.coord = coord;
		this.shootState = shootState;
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public ShootState getShootState() {
		return shootState;
	}
	
	public String toString() {
		switch (shootState) {
			case TOUCHED:
				return "Touched";
			case SINK:
				return "Sink";
			default:
				return "Missed";
		}
	}
}