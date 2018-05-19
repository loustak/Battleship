package sardois.lucas.Battleship.Core;

public class CoordShoot extends Coord {
	
	private ShootState shootState;
	
	public CoordShoot(int horizontal, int vertical) {
		super(horizontal, vertical);
		shootState = ShootState.NOT_SHOOT;
	}
	
	public CoordShoot(Coord coord, ShootState shootState) {
		super(coord);
		this.shootState = shootState;
	}
	
	public ShootState getShootState() {
		return shootState;
	}
	
	public void setShootState(ShootState shootState) {
		this.shootState = shootState;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
		
		switch (shootState) {
			case TOUCHED:
				stringBuilder.append(": touched");
				break;
			case SINK:
				stringBuilder.append(": sink");
				break;
			case MISSED:
				stringBuilder.append(": missed");
				break;
			default:
				break;
		}
		
		return stringBuilder.toString();
	}
}