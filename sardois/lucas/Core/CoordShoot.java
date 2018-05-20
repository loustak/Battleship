package sardois.lucas.Core;

/**
 * This class is used to represent a coordinate with 
 * an associated shoot
 * @author Lucas
 * 
 */
public class CoordShoot extends Coord {
	
	private ShootState shootState;
	
	/**
	 * Create a new CoordShoot initialized with a ShootState set to NOT_SHOOT
	 * @param horizontal the horizontal value of the coordinate
	 * @param vertical the vertical value of the coordinate
	 */
	public CoordShoot(int horizontal, int vertical) {
		super(horizontal, vertical);
		shootState = ShootState.NOT_SHOOT;
	}
	
	/**
	 * Create a new CoordShoot at the designated coordinate with the given shootState
	 * @param coord the coordinate
	 * @param shootState the shoot state
	 */
	public CoordShoot(Coord coord, ShootState shootState) {
		super(coord);
		this.shootState = shootState;
	}
	
	/**
	 * 
	 * @return the shoot state
	 */
	public ShootState getShootState() {
		return shootState;
	}
	
	/**
	 * 
	 * @param shootState set the shoot state
	 */
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