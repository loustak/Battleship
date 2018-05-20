package sardois.lucas.Core;

/**
 * All the states possible for any coordinate of a battleship game
 * @author Lucas
 *
 */
public enum ShootState {
	/**
	 * This place was never shoot
	 */
	NOT_SHOOT,
	
	/**
	 * A ship was touched on this place
	 */
	TOUCHED,
	
	/**
	 * A ship was sinked on this place
	 */
	SINK,
	
	/**
	 * This place has already been shoot and nothing was there
	 */
	MISSED
}