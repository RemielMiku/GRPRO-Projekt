package itumulator.world;

public class Hole implements NonBlocking {
    private int x;
    private int y;

    public Hole(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }
}
