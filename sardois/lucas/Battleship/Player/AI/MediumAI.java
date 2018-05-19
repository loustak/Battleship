package sardois.lucas.Battleship.Player.AI;

import sardois.lucas.Battleship.Core.Coord;
import sardois.lucas.Battleship.Core.CoordShoot;
import sardois.lucas.Battleship.Player.Player;

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
