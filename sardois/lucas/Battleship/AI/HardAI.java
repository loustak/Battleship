package sardois.lucas.Battleship.AI;

import java.util.ArrayList;

import sardois.lucas.Battleship.Coord;
import sardois.lucas.Battleship.Player;
import sardois.lucas.Battleship.Shoot;
import sardois.lucas.Battleship.ShootState;

public class HardAI extends AIPlayer {
	
	private ArrayList<Coord> huntShoot;

	public HardAI() {
		super("Hard AI");
		huntShoot = new ArrayList<Coord>();
	}
	
	private void addToHunt(Coord coord) {
		if (firedAt(coord) == null) {
			huntShoot.add(coord);
		}
	}
	
	private void addShootAround(Coord coord) {
		int horizontal = coord.getCoordHorizontal();
		int vertical = coord.getCoordVertical();
		
		if (vertical + 1 < Coord.getMaxCoord().getCoordVertical()) {
			addToHunt(new Coord(horizontal, vertical + 1));
		}
		
		if (horizontal + 1 < Coord.getMaxCoord().getCoordHorizontal()) {
			addToHunt(new Coord(horizontal + 1, vertical));
		}
		
		if (vertical - 1 > Coord.getMinCoord().getCoordVertical()) {
			addToHunt(new Coord(horizontal, vertical - 1));
		}
		
		if (horizontal - 1 > Coord.getMinCoord().getCoordHorizontal()) {
			addToHunt(new Coord(horizontal - 1, vertical));
		}
	}

	@Override
	public Shoot shoot(Player ennemyPlayer) {
		Coord randomCoord = null;
		Shoot previousShoot = null;
		if (!shoots.isEmpty()) {
			previousShoot = shoots.get(shoots.size() - 1);
		}
		
		if (huntShoot.isEmpty()) {
			if (previousShoot != null && previousShoot.getShootState() == ShootState.TOUCHED) {
				Coord shootCoord = previousShoot.getCoord();
				addShootAround(shootCoord);
			}
		}
		
		if (!huntShoot.isEmpty()) {
			if (previousShoot != null && previousShoot.getShootState() == ShootState.SINK) {
				huntShoot.clear();
			} else {
				randomCoord = huntShoot.remove(0);
			}
		}
		
		if (randomCoord == null) {
			do {
				randomCoord = Coord.getRandomCoord(0);
			} while (firedAt(randomCoord) != null);
		}
	
		return shootAt(ennemyPlayer, randomCoord);
	}
	
	@Override
	public void reset() {
		huntShoot.clear();
	}

}
