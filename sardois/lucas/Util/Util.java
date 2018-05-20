package sardois.lucas.Util;

/**
 * Utility fonctions that can be used in others projects
 * @author Lucas
 *
 */
public class Util {
	
	/**
	 * 
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return an int between minimum and maximum inclusive
	 */
    public static int randomRange(int min, int max) {
    	int range = (max - min) + 1;
    	return (int)(Math.random() * range) + min;
    }
}
