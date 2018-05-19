package sardois.lucas.Battleship.Player.AI;

import sardois.lucas.Battleship.Core.Coord;
import sardois.lucas.Battleship.Core.CoordShoot;
import sardois.lucas.Battleship.Player.Player;

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
