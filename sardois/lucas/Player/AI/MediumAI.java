package sardois.lucas.Player.AI;

import sardois.lucas.Core.Coord;
import sardois.lucas.Core.CoordShoot;
import sardois.lucas.Player.Player;

/**
 * This AI shoot randomly but never twice on the same position
 * @author Lucas
 *
 */
public class MediumAI extends AIPlayer {

	/**
	 * Construct a medium AI
	 */
	public MediumAI() {
		super("Medium AI");
	}

	@Override
	public CoordShoot shoot(Player ennemyPlayer) {
		Coord randomCoord = null;
		do {
			randomCoord = Coord.getRandomCoord(0);
		} while (firedAt(randomCoord) != null);
		
		return shootAt(ennemyPlayer, randomCoord);
	}

}
