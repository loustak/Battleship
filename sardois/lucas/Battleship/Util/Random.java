package sardois.lucas.Battleship.Util;

public class Random {
	
    public static int randomRange(int min, int max) {
    	int range = (max - min) + 1;
    	return (int)(Math.random() * range) + min;
    }
}
