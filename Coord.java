
public class Coord {

    private static Coord minCoord = new Coord("A1");
    private static Coord maxCoord = new Coord("J10");

    static public Coord getMinCoord() {
        return minCoord;
    }

    static public Coord getMaxCoord() {
        return maxCoord;
    }

    private int coord1;
    private int coord2;
    private boolean hit = false;

    public Coord() { 

    }

    public Coord(String coord) throws java.lang.Exception {
        int length = coord.length();
        if (length <= minCoord.toString().length() || length > maxCoord.toString().length()) {
            throw new Exception("Coordonnée invalide");
        }

        try {
            coord1 = (int) coord.charAt(0);
            String secondPart = coord.substring(1);
            coord2 = Integer.parseInt(secondPart);   
        } catch (Exception e) {
            throw new Exception("Type des coordonnées invalide");
        }

        if (coord1 >= minCoord.coord1 && coord2 >= minCoord.coord2 && 
            coord1 <= maxCoord.coord1 && coord2 <= maxCoord.coord2) {
            throw new Exception("Coordonnées hors de la grille");
        }
    }

    public Coord(int coord1, int coord2) {
        this.coord1 = coord1;
        this.coord2 = coord2;
    }

    public int getCoord1() {
        return coord1;
    }

    public int getCoord2() {
        return coord2;
    }

    public void setHit() {
        // fire on this coordinate
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Coord other = (Coord) obj;
        return getCoord1() == other.getCoord1() && getCoord2() == other.getCoord2();
    }

    public String toString() {
        return ((char)getCoord1()) + Integer.toString(getCoord2());
    }
}