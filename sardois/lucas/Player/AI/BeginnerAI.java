package sardois.lucas.Player.AI;

import sardois.lucas.Core.Coord;
import sardois.lucas.Core.CoordShoot;
import sardois.lucas.Player.Player;

/**
 * A beginner AI player. It will shoot randomly
 * @author Lucas
 * 
 */
public class BeginnerAI extends AIPlayer {

	/**
	 * Pass the name of the AI to it's parent
	 */
	public BeginnerAI() {
		super("Beginner AI");
	}

	@Override
	public CoordShoot shoot(Player ennemyPlayer) {
		Coord randomCoord = Coord.getRandomCoord(0);
		return shootAt(ennemyPlayer, randomCoord);
	}
}
