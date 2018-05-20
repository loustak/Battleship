package sardois.lucas.Player.AI;

import sardois.lucas.Core.Coord;
import sardois.lucas.Core.CoordShoot;
import sardois.lucas.Player.Player;

public class MediumAI extends AIPlayer {

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
