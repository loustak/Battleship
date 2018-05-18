package sardois.lucas.Battleship.AI;

import sardois.lucas.Battleship.Coord;
import sardois.lucas.Battleship.Player;
import sardois.lucas.Battleship.Shoot;

public class BeginnerAI extends AIPlayer {

	public BeginnerAI() {
		super("Beginner AI");
	}

	@Override
	public Shoot shoot(Player ennemyPlayer) {
		Coord randomCoord = Coord.getRandomCoord(0);
		return shootAt(ennemyPlayer, randomCoord);
	}
}
