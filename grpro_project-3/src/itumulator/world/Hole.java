package itumulator.world;
import java.awt.Color;
import itumulator.executable.DisplayInformation;

public class Hole implements NonBlocking {
    private final int x;
    private final int y;

    
    public Hole(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
