package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Coord;
import sardois.lucas.Battleship.Player;
import sardois.lucas.Battleship.Shoot;

public class MediumAI extends AIPlayer {

	public MediumAI() {
		super("Medium AI");
	}

	@Override
	public Shoot shoot(Player ennemyPlayer) {
		Coord randomCoord = null;
		do {
			randomCoord = Coord.getRandomCoord(0);
		} while (firedAt(randomCoord) != null);
		
		return shootAt(ennemyPlayer, randomCoord);
	}

}
