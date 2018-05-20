package sardois.lucas.Player.AI;

import sardois.lucas.Core.Coord;
import sardois.lucas.Core.CoordShoot;
import sardois.lucas.Player.Player;

public class BeginnerAI extends AIPlayer {

	public BeginnerAI() {
		super("Beginner AI");
	}

	@Override
	public CoordShoot shoot(Player ennemyPlayer) {
		Coord randomCoord = Coord.getRandomCoord(0);
		return shootAt(ennemyPlayer, randomCoord);
	}
}
