package sardois.lucas.Battleship;

public interface IPlayer {
	
	public void placeFleet(int shipSizes[]);
	
	public Ship placeShip(int shipSize);
	
	public Shoot shoot();
}
